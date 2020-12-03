package com.senla.courses.service;

import com.senla.courses.domain.GroupWall;
import com.senla.courses.domain.GroupWallMessage;
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
    private GroupWallRepository groupWallRepository;
    @Autowired
    private GroupWallMessageRepository groupWallMessageRepository;

    @Override
    @Transactional
    public void postMessage(int groupWallId, GroupWallMessage message) {
        try {
            GroupWall groupWall = groupWallRepository.findById(groupWallId).get();
            groupWall.getMessages().add(message);
            if (message.getGroupWall() == null) {
                message.setGroupWall(groupWall);
            }
            groupWallMessageRepository.save(message);
            groupWallRepository.save(groupWall);
            log.info(String.format("Message %d successfully posted on the wall of the %s group", message.getId(), groupWall.getGroup().getTitle()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void removeMessage(int groupWallId, int messageId) {
        try {
            GroupWall groupWall = groupWallRepository.findById(groupWallId).get();
            GroupWallMessage message = groupWallMessageRepository.findById(messageId).get();
            groupWallMessageRepository.delete(message);
            log.info(String.format("Message %d successfully removed from the wall of the %s group", message.getId(), groupWall.getGroup().getTitle()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
