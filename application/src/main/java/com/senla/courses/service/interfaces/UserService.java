package com.senla.courses.service.interfaces;

import com.senla.courses.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void addUser(User user);
}
