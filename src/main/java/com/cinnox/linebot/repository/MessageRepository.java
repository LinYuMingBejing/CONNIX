package com.cinnox.linebot.repository;

import com.cinnox.linebot.entity.MessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MessageRepository extends MongoRepository<MessageEntity, String> {
    @Query("{userId:?0}")
    List<MessageEntity> findByUserId(String userId);
}
