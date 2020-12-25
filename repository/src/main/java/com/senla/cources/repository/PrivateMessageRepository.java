package com.senla.cources.repository;

import com.senla.cources.domain.PrivateMessage;
import com.senla.cources.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, Integer> {

    List<PrivateMessage> getPrivateMessagesBySenderAndReceiver(User sender, User receiver);
}
