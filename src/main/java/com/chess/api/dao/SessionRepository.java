package com.chess.api.dao;

import com.chess.api.data.Session;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepository extends MongoRepository<Session, Long> {
}
