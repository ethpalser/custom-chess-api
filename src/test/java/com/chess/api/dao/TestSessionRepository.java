package com.chess.api.dao;

import com.chess.api.data.Session;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestSessionRepository {

    @Autowired
    private SessionRepository sessionRepository;

    private ObjectId testSessionId;

    Session testSession() {
        ObjectId white = new ObjectId();
        ObjectId black = new ObjectId();
        Session session = new Session();
        session.setId(this.testSessionId);
        session.setUserWhiteId(white);
        session.setUserBlackId(black);
        return session;
    }

    @BeforeAll()
    void beforeAll() {
        this.testSessionId = new ObjectId();
        // Only for DEV environment; todo: update to only be for DEV
        this.sessionRepository.deleteAll();
    }

    @Test
    void testCreateSession() {
        Session saved = this.sessionRepository.save(testSession());
        // Check it exists
        Session fetched = this.sessionRepository.findSessionById(saved.getId());
        Assertions.assertEquals(saved, fetched);
    }

    @Test
    void testUpdateSession() {
        Session fetched = this.sessionRepository.findSessionById(this.testSessionId);
        if (fetched == null) {
            fetched = this.sessionRepository.save(testSession());
        }
        fetched.setInProgress(true);
        sessionRepository.save(fetched);
        Assertions.assertEquals(fetched, this.sessionRepository.findSessionById(fetched.getId()));
    }

}
