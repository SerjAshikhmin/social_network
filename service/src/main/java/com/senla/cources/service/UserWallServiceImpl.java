package com.senla.cources.service;

import com.senla.cources.domain.User;
import com.senla.cources.domain.UserWall;
import com.senla.cources.domain.UserWallMessage;
import com.senla.cources.dto.UserWallMessageDto;
import com.senla.cources.dto.mappers.UserWallMessageMapper;
import com.senla.cources.exceptions.messageexceptions.PostMessageException;
import com.senla.cources.exceptions.messageexceptions.RemoveMessageException;
import com.senla.cources.repository.UserRepository;
import com.senla.cources.repository.UserWallMessageRepository;
import com.senla.cources.repository.UserWallRepository;
import com.senla.cources.service.interfaces.UserWallService;
import com.senla.cources.service.security.PermissionService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Setter
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
    @Autowired
    private PermissionService permissionService;

    @Override
    @Transactional
    public void postMessage(int userId, UserWallMessageDto messageDto) {
        log.debug(String.format("Call method postMessage with userId %d", userId));
        try {
            User user = userRepository.findById(userId).get();
            UserWall userWall = user.getUserWall();
            if (permissionService.checkPermissionForUserWall(user)) {
                UserWallMessage message = userWallMessageMapper.userWallMessageDtoToUserWallMessage(messageDto);
                userWall.getMessages().add(message);
                if (message.getUserWall() == null) {
                    message.setUserWall(userWall);
                }
                message.setId(null);
                userWall.getMessages().add(message);
                userWallRepository.save(userWall);
                log.info(String.format("Message %d successfully posted on the wall of user %s", message.getId(), user.getUserPrincipal().getUsername()));
            } else {
                log.warn(String.format("User %s doesn't have permission to post message at wall %d", user.getUserPrincipal().getUsername(), userWall.getId()));
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
            User user = userRepository.findById(userId).get();
            UserWall userWall = user.getUserWall();
            if (permissionService.checkPermissionForUserWall(user)) {
                UserWallMessage message = userWallMessageRepository.findById(messageId).get();
                userWallMessageRepository.delete(message);
                log.info(String.format("Message %d successfully removed from the wall of user %s", message.getId(), user.getUserPrincipal().getUsername()));
            } else {
                String message = String.format("User %s doesn't have permission to remove message at wall %d", user.getUserPrincipal().getUsername(), userWall.getId());
                log.warn(message);
                throw new RuntimeException(message);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RemoveMessageException(e);
        }
    }

}
