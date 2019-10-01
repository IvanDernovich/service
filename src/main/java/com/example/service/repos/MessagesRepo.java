package com.example.service.repos;

import com.example.service.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessagesRepo extends CrudRepository<Message, Integer> {

    List<Message> findByTag(String tag);
}
