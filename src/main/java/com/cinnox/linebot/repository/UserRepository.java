package com.cinnox.linebot.repository;

import com.cinnox.linebot.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends MongoRepository<UserEntity, String> {
}
