package com.senla.courses.service;

import com.senla.courses.domain.UserWall;
import com.senla.courses.domain.UserWallMessage;
import com.senla.courses.repository.UserWallMessageRepository;
import com.senla.courses.repository.UserWallRepository;
import com.senla.courses.service.interfaces.UserWallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserWallServiceImpl implements UserWallService {

    @Autowired
    private UserWallRepository userWallRepository;
    @Autowired
    private UserWallMessageRepository userWallMessageRepository;

    @Override
    public void postMessage(int userWallId, UserWallMessage message) {
        try {
            UserWall userWall = userWallRepository.findById(userWallId).get();
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            if (userWall.getUser().getUserPrincipal().getUsername().equals(userName)) {
                userWall.getMessages().add(message);
                if (message.getUserWall() == null) {
                    message.setUserWall(userWall);
                }
                userWall.getMessages().add(message);
                userWallRepository.save(userWall);
                log.info(String.format("Message %d successfully posted on the wall of user %s", message.getId(), userWall.getUser().getUserPrincipal().getUsername()));
            } else {
                log.warn(String.format("User %s isn't owner of wall %d", userWall.getUser().getUserPrincipal().getUsername(), userWall.getId()));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void removeMessage(int userWallId, int messageId) {
        try {
            UserWall userWall = userWallRepository.findById(userWallId).get();
            UserWallMessage message = userWallMessageRepository.findById(messageId).get();
            userWallMessageRepository.delete(message);
            log.info(String.format("Message %d successfully removed from the wall of user %s", message.getId(), userWall.getUser().getUserPrincipal().getUsername()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
