package com.senla.cources.service;

import com.senla.cources.domain.Group;
import com.senla.cources.domain.GroupWall;
import com.senla.cources.domain.GroupWallMessage;
import com.senla.cources.dto.GroupWallMessageDto;
import com.senla.cources.dto.mappers.GroupWallMessageMapper;
import com.senla.cources.exceptions.messageexceptions.PostMessageException;
import com.senla.cources.exceptions.messageexceptions.RemoveMessageException;
import com.senla.cources.repository.GroupRepository;
import com.senla.cources.repository.GroupWallMessageRepository;
import com.senla.cources.repository.GroupWallRepository;
import com.senla.cources.repository.UserPrincipalRepository;
import com.senla.cources.service.interfaces.GroupWallService;
import com.senla.cources.service.security.PermissionService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Setter
@Service
public class GroupWallServiceImpl implements GroupWallService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupWallRepository groupWallRepository;
    @Autowired
    private GroupWallMessageRepository groupWallMessageRepository;
    @Autowired
    private GroupWallMessageMapper groupWallMessageMapper;
    @Autowired
    private PermissionService permissionService;

    @Override
    @Transactional
    public void postMessage(int groupId, GroupWallMessageDto messageDto) {
        log.debug(String.format("Call method postMessage with groupId %d", groupId));
        try {
            Group group = groupRepository.findById(groupId).get();
            GroupWall groupWall = group.getGroupWall();
            if (permissionService.checkPermissionForGroup(group)) {
                GroupWallMessage message = groupWallMessageMapper.groupWallMessageDtoToGroupWallMessage(messageDto);
                groupWall.getMessages().add(message);
                if (message.getGroupWall() == null) {
                    message.setGroupWall(groupWall);
                }
                message.setId(null);
                groupWallMessageRepository.save(message);
                groupWallRepository.save(groupWall);
                log.info(String.format("Message %d successfully posted on the wall of the %s group", message.getId(), group.getTitle()));
            } else {
                String message = String.format("Current user doesn't have permission to post message in group %s", group.getTitle());
                log.warn(message);
                throw new RuntimeException(message);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new PostMessageException(e);
        }
    }

    @Override
    @Transactional
    public void removeMessage(int groupId, int messageId) {
        log.debug(String.format("Call method removeMessage with groupId %d, messageId %d", groupId, messageId));
        try {
            Group group = groupRepository.findById(groupId).get();
            GroupWall groupWall = group.getGroupWall();
            if (permissionService.checkPermissionForGroup(group)) {
                GroupWallMessage message = groupWallMessageRepository.findById(messageId).get();
                groupWall.getMessages().remove(message);
                groupWallMessageRepository.delete(message);
                log.info(String.format("Message %d successfully removed from the wall of the %s group", message.getId(), group.getTitle()));
            } else {
                log.info(String.format("Current user doesn't have permission to remove message in group %s", group.getTitle()));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RemoveMessageException(e);
        }
    }

}
