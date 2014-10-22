package info.archinnov.achilles.demo.service;

import info.archinnov.achilles.demo.entity.User;
import info.archinnov.achilles.exception.AchillesLightWeightTransactionException;
import info.archinnov.achilles.junit.AchillesResource;
import info.archinnov.achilles.junit.AchillesResourceBuilder;
import info.archinnov.achilles.persistence.PersistenceManager;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static info.archinnov.achilles.demo.referential.SchemaName.KEYSPACE;
import static info.archinnov.achilles.demo.referential.SchemaName.USER;


@RunWith(MockitoJUnitRunner.class)
public class UserScenario2Test {

    @Rule
    public AchillesResource resource = AchillesResourceBuilder
            .withEntityPackages(User.class.getPackage().getName())
            .withKeyspaceName(KEYSPACE)
            .tablesToTruncate(USER)
            .truncateBeforeAndAfterTest().build();

    private PersistenceManager manager = resource.getPersistenceManager();

    private UserService service = new UserService();

    @Before
    public void setUp() {
        service.manager = this.manager;
    }


    @Test(expected = AchillesLightWeightTransactionException.class)
    public void should_fail_creating_user_if_already_exist() throws Exception {
        //Given
        service.createUser("emc²", "Albert", "EINSTEIN");

        //When
        service.createUser("emc²", "Albert", "EINSTEIN");
    }
}
