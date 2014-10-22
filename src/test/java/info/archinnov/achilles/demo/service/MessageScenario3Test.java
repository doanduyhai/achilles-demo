package info.archinnov.achilles.demo.service;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Select;
import info.archinnov.achilles.demo.entity.Message;
import info.archinnov.achilles.demo.interceptors.LoginToLowerCase;
import info.archinnov.achilles.interceptor.Interceptor;
import info.archinnov.achilles.junit.AchillesResource;
import info.archinnov.achilles.junit.AchillesResourceBuilder;
import info.archinnov.achilles.persistence.PersistenceManager;
import org.apache.cassandra.utils.UUIDGen;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.UUID;

import static com.datastax.driver.core.querybuilder.QueryBuilder.*;
import static info.archinnov.achilles.demo.referential.SchemaName.KEYSPACE_FOR_INTERCEPTOR;
import static info.archinnov.achilles.demo.referential.SchemaName.MAILBOX;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MessageScenario3Test {

    @Rule
    public AchillesResource resource = AchillesResourceBuilder
            .withEntityClasses(Arrays.<Class<?>>asList(Message.class))
            .withKeyspaceName(KEYSPACE_FOR_INTERCEPTOR)
            .withInterceptors(Arrays.<Interceptor<?>>asList(new LoginToLowerCase()))
            .tablesToTruncate(MAILBOX)
            .truncateBeforeAndAfterTest().build();

    private PersistenceManager manager = resource.getPersistenceManager();

    private Session session = resource.getNativeSession();

    private MessageService service = new MessageService();

    @Before
    public void setUp() {
        service.manager = this.manager;
    }

    @Test
    public void should_lower_case_interlocutor_name() throws Exception {
        //Given
        UUID date = UUIDGen.getTimeUUID();

        //When
        service.createMessage("emc²",date,"Max_PLANK","Hey buddy, I've just discovered the secret of the universe: E = mc²");

        //Then
        final Select.Where select = select().from(MAILBOX).where(eq("login", "emc²"));

        final Row row = session.execute(select).one();

        assertThat(row).isNotNull();
        assertThat(row.getUUID("date")).isEqualTo(date);
        assertThat(row.getString("interlocutor")).isEqualTo("max_plank");
        assertThat(row.getString("content")).isEqualTo("Hey buddy, I've just discovered the secret of the universe: E = mc²");
    }
}
