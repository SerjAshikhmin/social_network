package com.senla.cources.service.interfaces;

import com.senla.cources.dto.GroupWallMessageDto;
import org.springframework.stereotype.Service;

@Service
public interface GroupWallService {

    void postMessage(int groupId, GroupWallMessageDto message);
    void removeMessage(int groupId, int messageId);
}
