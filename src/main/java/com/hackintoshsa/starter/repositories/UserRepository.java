package com.hackintoshsa.starter.repositories;

import com.hackintoshsa.starter.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findOneByEmail(String email);
    User findByEmailAndVerifiedFalse(String email);

}
