package info.archinnov.achilles.demo.service;


import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import info.archinnov.achilles.demo.entity.Message;
import info.archinnov.achilles.persistence.PersistenceManager;

public class MessageService {

    @Inject
    PersistenceManager manager;

    public void createMessage(String login, UUID date, String interlocutor, String content) {

    }

    public List<Message> fetchMessages(String login, UUID lastMessageDate, int pageSize) {

        return null;

    }
}
