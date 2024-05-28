package com.chess.api.dao;

import com.chess.api.data.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, Long> {

    @Query("{_id:$eq{'?0'}}")
    User findUserById(long id);

    @Query("{token:$eq{'?0'}}")
    User findUserByToken(String token);


}
