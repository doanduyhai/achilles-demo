package info.archinnov.achilles.demo.service;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.insertInto;
import static com.datastax.driver.core.querybuilder.QueryBuilder.select;
import static info.archinnov.achilles.demo.referential.SchemaName.KEYSPACE;
import static info.archinnov.achilles.demo.referential.SchemaName.USER;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Insert;
import info.archinnov.achilles.demo.entity.User;
import info.archinnov.achilles.junit.AchillesResource;
import info.archinnov.achilles.junit.AchillesResourceBuilder;
import info.archinnov.achilles.persistence.PersistenceManager;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class UserScenario1Test {

    @Rule
    public AchillesResource resource = AchillesResourceBuilder
            .withEntityPackages(User.class.getPackage().getName())
            .withKeyspaceName(KEYSPACE)
            .tablesToTruncate(USER)
            .truncateBeforeAndAfterTest().build();

    private PersistenceManager manager = resource.getPersistenceManager();

    private Session session = resource.getNativeSession();

    private UserService service = new UserService();

    @Before
    public void setUp() {
        service.manager = this.manager;
    }

    @Test
    public void should_create_user() throws Exception {
        //Given

        //When
        service.createUser("emc²", "Albert", "EINSTEIN");

        //Then
        final Row row = session.execute(select().from(USER).where(eq("login", "emc²"))).one();

        assertThat(row).isNotNull();
        assertThat(row.getString("login")).isEqualTo("emc²");
        assertThat(row.getString("firstname")).isEqualTo("Albert");
        assertThat(row.getString("lastname")).isEqualTo("EINSTEIN");
    }

    @Test
    public void should_find_user_by_id() throws Exception {
        //Given
        final Insert insert = insertInto(USER).value("login", "emc²").value("firstname", "Albert").value("lastname", "EINSTEIN");
        session.execute(insert);

        //When
        final User foundUser = service.findByLogin("emc²");

        //Then
        assertThat(foundUser.getFirstname()).isEqualTo("Albert");
        assertThat(foundUser.getLastname()).isEqualTo("EINSTEIN");
    }
}
