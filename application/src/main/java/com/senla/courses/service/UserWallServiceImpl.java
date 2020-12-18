package com.senla.courses.service;

import com.senla.courses.domain.UserWall;
import com.senla.courses.domain.UserWallMessage;
import com.senla.courses.dto.UserWallMessageDto;
import com.senla.courses.dto.mappers.UserWallMessageMapper;
import com.senla.courses.exceptions.messageexceptions.PostMessageException;
import com.senla.courses.exceptions.messageexceptions.RemoveMessageException;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.repository.UserWallMessageRepository;
import com.senla.courses.repository.UserWallRepository;
import com.senla.courses.service.interfaces.UserWallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserWallServiceImpl implements UserWallService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserWallRepository userWallRepository;
    @Autowired
    private UserWallMessageRepository userWallMessageRepository;
    @Autowired
    private UserWallMessageMapper userWallMessageMapper;

    @Override
    @Transactional
    public void postMessage(int userId, UserWallMessageDto messageDto) {
        log.debug(String.format("Call method postMessage with userId %d", userId));
        try {
            UserWall userWall = userRepository.findById(userId).get().getUserWall();
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            if (userWall.getUser().getUserPrincipal().getUsername().equals(userName)) {
                UserWallMessage message = userWallMessageMapper.userWallMessageDtoToUserWallMessage(messageDto);
                userWall.getMessages().add(message);
                if (message.getUserWall() == null) {
                    message.setUserWall(userWall);
                }
                message.setId(null);
                userWall.getMessages().add(message);
                userWallRepository.save(userWall);
                log.info(String.format("Message %d successfully posted on the wall of user %s", message.getId(), userWall.getUser().getUserPrincipal().getUsername()));
            } else {
                log.warn(String.format("User %s isn't owner of wall %d", userWall.getUser().getUserPrincipal().getUsername(), userWall.getId()));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new PostMessageException(e);
        }
    }

    @Override
    @Transactional
    public void removeMessage(int userId, int messageId) {
        log.debug(String.format("Call method removeMessage with userId %d, messageId %d", userId, messageId));
        try {
            UserWall userWall = userRepository.findById(userId).get().getUserWall();
            UserWallMessage message = userWallMessageRepository.findById(messageId).get();
            userWallMessageRepository.delete(message);
            log.info(String.format("Message %d successfully removed from the wall of user %s", message.getId(), userWall.getUser().getUserPrincipal().getUsername()));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RemoveMessageException(e);
        }
    }
}
