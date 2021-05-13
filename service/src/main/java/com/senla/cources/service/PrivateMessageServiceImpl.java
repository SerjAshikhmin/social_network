package com.senla.cources.service;

import com.senla.cources.domain.Message;
import com.senla.cources.domain.PrivateMessage;
import com.senla.cources.domain.User;
import com.senla.cources.dto.PrivateMessageDto;
import com.senla.cources.dto.mappers.PrivateMessageMapper;
import com.senla.cources.dto.mappers.UserMapper;
import com.senla.cources.exceptions.messageexceptions.ReceiveMessageException;
import com.senla.cources.exceptions.messageexceptions.SendMessageException;
import com.senla.cources.exceptions.messageexceptions.ShowDialogException;
import com.senla.cources.repository.PrivateMessageRepository;
import com.senla.cources.repository.UserPrincipalRepository;
import com.senla.cources.repository.UserRepository;
import com.senla.cources.service.interfaces.PrivateMessageService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Setter
@Service
public class PrivateMessageServiceImpl implements PrivateMessageService {

    @Autowired
    private PrivateMessageRepository privateMessageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPrincipalRepository userPrincipalRepository;
    @Autowired
    private PrivateMessageMapper privateMessageMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    @Transactional
    public void sendMessage(PrivateMessageDto messageDto, int receiverId) {
        log.debug(String.format("Call method sendMessage with receiverId %d", receiverId));
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            User currentUser = userPrincipalRepository.findMyUserPrincipalByUserName(currentUserName).getUser();
            User receiver = userRepository.findById(receiverId).get();
            PrivateMessage message = privateMessageMapper.privateMessageDtoToPrivateMessage(messageDto);
            message.setSender(currentUser);
            message.setReceiver(receiver);
            message.setId(null);
            //message = privateMessageRepository.save(message);
            messageDto.setSender(userMapper.userToUserDto(currentUser));
            messageDto.setReceiver(userMapper.userToUserDto(receiver));
            messageDto.setSendDate(LocalDateTime.now());
            jmsTemplate.convertAndSend(currentUserName + receiver.getUserPrincipal().getUsername(), messageDto);
            log.info(String.format("Message %d successfully sent by user %s to user %s",
                                    message.getId(), currentUserName, receiver.getUserPrincipal().getUsername()));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SendMessageException(e);
        }
    }

    @Override
    @Transactional
    public void receiveMessages(int senderId) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        log.debug(String.format("Call method receiveMessage for userName %s", currentUserName));
        try {
            User sender = userRepository.findById(senderId).get();
            jmsTemplate.setReceiveTimeout(100);
            while (true) {
                PrivateMessageDto messageDto = (PrivateMessageDto) jmsTemplate.receiveAndConvert(currentUserName + sender.getUserPrincipal().getUsername());
                if (messageDto != null) {
                    PrivateMessage message = privateMessageMapper.privateMessageDtoToPrivateMessage(messageDto);
                    message = privateMessageRepository.save(message);
                    log.info(String.format("Message %d successfully received by user %s", message.getId(), currentUserName));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ReceiveMessageException(e);
        }
    }

    @Override
    public List<PrivateMessageDto> showDialog(int userId) {
        log.debug(String.format("Call method showDialog with userId %d", userId));
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        List<PrivateMessage> result;
        try {
            User currentUser = userPrincipalRepository.findMyUserPrincipalByUserName(currentUserName).getUser();
            User user = userRepository.findById(userId).get();
            result = privateMessageRepository.getPrivateMessagesBySenderAndReceiver(currentUser, user);
            result.addAll(privateMessageRepository.getPrivateMessagesBySenderAndReceiver(user, currentUser));
            result.sort(Comparator.comparing(Message::getSendDate));
            for (PrivateMessage message : result) {
                if (!message.isRead() && message.getReceiver().equals(currentUser)) {
                    message.setRead(true);
                    privateMessageRepository.save(message);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ShowDialogException(e);
        }
        return privateMessageMapper.privateMessageListToPrivateMessageDtoList(result);
    }
}
