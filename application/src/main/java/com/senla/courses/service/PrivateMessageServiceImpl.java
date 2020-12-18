package com.senla.courses.service;

import com.senla.courses.domain.Message;
import com.senla.courses.domain.PrivateMessage;
import com.senla.courses.domain.User;
import com.senla.courses.dto.PrivateMessageDto;
import com.senla.courses.dto.mappers.PrivateMessageMapper;
import com.senla.courses.exceptions.messageexceptions.SendMessageException;
import com.senla.courses.exceptions.messageexceptions.ShowDialogException;
import com.senla.courses.repository.PrivateMessageRepository;
import com.senla.courses.repository.UserPrincipalRepository;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.service.interfaces.PrivateMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Slf4j
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

    @Override
    @Transactional
    public void sendMessage(PrivateMessageDto messageDto, int receiverId) {
        log.debug(String.format("Call method sendMessage with receiverId %d", receiverId));
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            User currentUser = userPrincipalRepository.findMyUserPrincipalByUserName(currentUserName).getUser();
            User user = userRepository.findById(receiverId).get();
            PrivateMessage message = privateMessageMapper.privateMessageDtoToPrivateMessage(messageDto);
            message.setSender(currentUser);
            message.setReceiver(user);
            message.setId(null);
            privateMessageRepository.save(message);
            log.info(String.format("Message %d successfully sent by user %s to user %s",
                                    message.getId(), currentUserName, user.getUserPrincipal().getUsername()));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SendMessageException(e);
        }
    }

    @Override
    public List<PrivateMessageDto> showDialog(int userId) {
        log.debug(String.format("Call method showDialog with userId %d", userId));
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        List<PrivateMessage> result = null;
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
