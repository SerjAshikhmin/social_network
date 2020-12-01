package com.senla.courses.service;

import com.senla.courses.domain.User;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }
}
