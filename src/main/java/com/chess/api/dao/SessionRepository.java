package com.chess.api.dao;

import com.chess.api.data.Session;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SessionRepository extends MongoRepository<Session, Long> {

    @Query("{_id:$eq{'?0'}}")
    Session findSessionById(long id);

    @Query("{inProgress:$eq{'?0'}}")
    List<Session> findAllByInProgress(boolean inProgress);

}
