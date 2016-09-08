package info.archinnov.achilles.demo.c_advanced_usage;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.google.common.collect.ImmutableMap;

import info.archinnov.achilles.demo.a_bean_mapping.EmailMessage;
import info.archinnov.achilles.demo.d_power_users.Interlocutor;
import info.archinnov.achilles.generated.ManagerFactory;
import info.archinnov.achilles.generated.ManagerFactoryBuilder;
import info.archinnov.achilles.junit.AchillesTestResource;
import info.archinnov.achilles.junit.AchillesTestResourceBuilder;
import info.archinnov.achilles.script.ScriptExecutor;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    @Rule
    public AchillesTestResource<ManagerFactory> resource = AchillesTestResourceBuilder
            .forJunit()
            .createAndUseKeyspace("production_ks")
            .entityClassesToTruncate(EmailMessage.class)
            .truncateBeforeAndAfterTest()
            .build((cluster,statementCache) -> ManagerFactoryBuilder
                    .builder(cluster)
                    .withStatementsCache(statementCache)
                    .doForceSchemaCreation(true)
                    .withDefaultKeyspaceName("production_ks")
                    .build()
            );

    private Session session = resource.getNativeSession();
    private ScriptExecutor scriptExecutor = resource.getScriptExecutor();
    private EmailService service = new EmailService(resource.getManagerFactory().forEmailMessage());

    @Test
    public void should_create_email() throws Exception {
        //Given
        String subject = "Contact email";
        String body = "Hello John, how do you do ?";
        Interlocutor sender = new Interlocutor("helenSue", "hsue@gmail.com");
        List<Interlocutor> recipients = Arrays.asList(new Interlocutor("johnDoe", "jdoe@gmail.com"));

        //When
        service.createEmail("johnDoe", "MAIN", sender, recipients, subject, body);

        //Then
        final Row row = session.execute("SELECT * FROM emails WHERE userlogin='johnDoe' AND inboxtype='MAIN'").one();

        assertThat(row.getString("subject")).isEqualTo(subject);
        assertThat(row.getString("body")).isEqualTo(body);
    }
    
    @Test
    public void should_fetch_last_emails() throws Exception {
        //Given
        String randomLogin = RandomStringUtils.randomAlphabetic(10);
        scriptExecutor.executeScriptTemplate("insert_emails.cql", ImmutableMap.of("login", randomLogin));
       
        //When
        final List<EmailMessage> lastEmails = service.getLastEmails(randomLogin, "MAIN", 3);

        //Then
        assertThat(lastEmails.get(0).getSubject()).isEqualTo("Fifth");
        assertThat(lastEmails.get(1).getSubject()).isEqualTo("Fourth");
        assertThat(lastEmails.get(2).getSubject()).isEqualTo("Third");
    }
}