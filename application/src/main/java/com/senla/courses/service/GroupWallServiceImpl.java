package com.senla.courses.service;

import com.senla.courses.domain.GroupWall;
import com.senla.courses.domain.GroupWallMessage;
import com.senla.courses.dto.GroupWallMessageDto;
import com.senla.courses.dto.mappers.GroupWallMessageMapper;
import com.senla.courses.exceptions.messageexceptions.PostMessageException;
import com.senla.courses.exceptions.messageexceptions.RemoveMessageException;
import com.senla.courses.repository.GroupRepository;
import com.senla.courses.repository.GroupWallMessageRepository;
import com.senla.courses.repository.GroupWallRepository;
import com.senla.courses.service.interfaces.GroupWallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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

    @Override
    @Transactional
    public void postMessage(int groupId, GroupWallMessageDto messageDto) {
        log.debug(String.format("Call method postMessage with groupId %d", groupId));
        try {
            GroupWall groupWall = groupRepository.findById(groupId).get().getGroupWall();
            GroupWallMessage message = groupWallMessageMapper.groupWallMessageDtoToGroupWallMessage(messageDto);
            groupWall.getMessages().add(message);
            if (message.getGroupWall() == null) {
                message.setGroupWall(groupWall);
            }
            message.setId(null);
            groupWallMessageRepository.save(message);
            groupWallRepository.save(groupWall);
            log.info(String.format("Message %d successfully posted on the wall of the %s group", message.getId(), groupWall.getGroup().getTitle()));
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
            GroupWall groupWall = groupRepository.findById(groupId).get().getGroupWall();
            GroupWallMessage message = groupWallMessageRepository.findById(messageId).get();
            groupWall.getMessages().remove(message);
            groupWallMessageRepository.delete(message);
            log.info(String.format("Message %d successfully removed from the wall of the %s group", message.getId(), groupWall.getGroup().getTitle()));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RemoveMessageException(e);
        }
    }
}
