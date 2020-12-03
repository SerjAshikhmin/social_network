package com.senla.courses.service.interfaces;

import com.senla.courses.domain.PrivateMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrivateMessageService {

    void sendMessage(PrivateMessage message, int receiverId);
    List<PrivateMessage> showDialog(int userId);
}
