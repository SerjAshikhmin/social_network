package com.senla.courses.service.interfaces;

import com.senla.courses.dto.PrivateMessageDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrivateMessageService {

    void sendMessage(PrivateMessageDto message, int receiverId);
    List<PrivateMessageDto> showDialog(int userId);
}
