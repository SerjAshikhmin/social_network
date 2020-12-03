package com.senla.courses.service.interfaces;

import com.senla.courses.domain.GroupWallMessage;
import org.springframework.stereotype.Service;

@Service
public interface GroupWallService {

    void postMessage(int groupWallId, GroupWallMessage message);
    void removeMessage(int groupWallId, int messageId);
}
