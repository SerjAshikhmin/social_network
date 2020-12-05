package com.senla.courses.service.interfaces;

import com.senla.courses.domain.UserWallMessage;
import com.senla.courses.dto.UserWallMessageDto;
import org.springframework.stereotype.Service;

@Service
public interface UserWallService {

    void postMessage(int userId, UserWallMessageDto message);
    void removeMessage(int userId, int messageId);
}
