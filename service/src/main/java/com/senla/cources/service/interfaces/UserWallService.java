package com.senla.cources.service.interfaces;

import com.senla.cources.dto.UserWallMessageDto;
import org.springframework.stereotype.Service;

@Service
public interface UserWallService {

    void postMessage(int userId, UserWallMessageDto message);
    void removeMessage(int userId, int messageId);
}
