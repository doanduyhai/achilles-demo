package info.archinnov.achilles.demo.service;

import info.archinnov.achilles.demo.entity.Message;
import info.archinnov.achilles.exception.AchillesBeanValidationException;
import info.archinnov.achilles.junit.AchillesResource;
import info.archinnov.achilles.junit.AchillesResourceBuilder;
import info.archinnov.achilles.persistence.PersistenceManager;
import org.apache.cassandra.utils.UUIDGen;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.UUID;

import static info.archinnov.achilles.demo.referential.SchemaName.KEYSPACE_FOR_BEAN_VALIDATION;
import static info.archinnov.achilles.demo.referential.SchemaName.MAILBOX;

@RunWith(MockitoJUnitRunner.class)
public class MessageScenario2Test {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public AchillesResource resource = AchillesResourceBuilder
            .withEntityClasses(Arrays.<Class<?>>asList(Message.class))
            .withKeyspaceName(KEYSPACE_FOR_BEAN_VALIDATION)
            .withBeanValidation()
            .tablesToTruncate(MAILBOX)
            .truncateBeforeAndAfterTest().build();

    private PersistenceManager manager = resource.getPersistenceManager();

    private MessageService service = new MessageService();

    @Before
    public void setUp() {
        service.manager = this.manager;
    }

    @Test(expected = AchillesBeanValidationException.class)
    public void should_fail_creating_message_if_empty_text() throws Exception {
        //Given
        UUID date = UUIDGen.getTimeUUID();

        //When
        service.createMessage("emc²",date,"max_plank","");
    }

    @Test
    public void should_fail_creating_message_if_empty_interlocutor() throws Exception {
        //Given
        UUID date = UUIDGen.getTimeUUID();

        //When
        exception.expect(AchillesBeanValidationException.class);
        exception.expectMessage("Bean validation error : \n" +
                "\tproperty 'interlocutor' of class 'info.archinnov.achilles.demo.entity.Message' may not be empty");
        service.createMessage("emc²",date,"","What ever message");
    }

}
