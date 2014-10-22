package info.archinnov.achilles.demo.service;


import javax.inject.Inject;
import info.archinnov.achilles.demo.entity.User;
import info.archinnov.achilles.persistence.PersistenceManager;
import info.archinnov.achilles.type.OptionsBuilder;

public class UserService {

    @Inject
    PersistenceManager manager;

    public void createUser(String login, String firstname, String lastname) {

    }

    public User findByLogin(String login) {
        return null;
    }
}
