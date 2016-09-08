package info.archinnov.achilles.demo.c_advanced_usage;

import java.util.List;

import com.datastax.driver.core.utils.UUIDs;

import info.archinnov.achilles.demo.a_bean_mapping.EmailMessage;
import info.archinnov.achilles.demo.d_power_users.Interlocutor;
import info.archinnov.achilles.generated.manager.EmailMessage_Manager;

public class EmailService {


    private EmailMessage_Manager manager;

    public EmailService(EmailMessage_Manager manager) {
        this.manager = manager;
    }

    public void createEmail(String login, String inboxType, Interlocutor sender, List<Interlocutor> recipients,
                            String subject, String body) {

        final EmailMessage email = new EmailMessage(login, inboxType, UUIDs.timeBased(), subject, sender, recipients, body);
        manager.crud().insert(email).ifNotExists().execute();
    }

    public List<EmailMessage> getLastEmails(String login, String inboxType, int limit) {
        return manager
                .dsl()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .userLogin().Eq(login)
                .inboxType().Eq(inboxType)
                .limit(limit)
                .orderByEmailIdDescending()
                .getList();
    }
}
