package com.senla.courses.repository;

import com.senla.courses.domain.PrivateMessage;
import com.senla.courses.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, Integer> {

    List<PrivateMessage> getPrivateMessagesBySenderAndReceiver(User sender, User receiver);
}
