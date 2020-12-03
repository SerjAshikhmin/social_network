package com.senla.courses.service.interfaces;

import com.senla.courses.domain.UserWallMessage;
import org.springframework.stereotype.Service;

@Service
public interface UserWallService {

    void postMessage(int userWallId, UserWallMessage message);
    void removeMessage(int userWallId, int messageId);
}
