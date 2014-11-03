package info.archinnov.achilles.demo.service;

import info.archinnov.achilles.demo.entity.UserTracking;
import info.archinnov.achilles.persistence.PersistenceManager;

import javax.inject.Inject;
import java.util.Date;

import static info.archinnov.achilles.demo.entity.UserTracking.Action;

public class StalkerService {

    @Inject
    PersistenceManager manager;

    public void insertUserAction(String login, Date date, Action action, Long itemId) {

    }
}
