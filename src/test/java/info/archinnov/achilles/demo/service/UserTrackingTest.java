package info.archinnov.achilles.demo.service;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Insert;
import info.archinnov.achilles.demo.entity.User;
import info.archinnov.achilles.demo.entity.UserTracking;
import info.archinnov.achilles.junit.AchillesResource;
import info.archinnov.achilles.junit.AchillesResourceBuilder;
import info.archinnov.achilles.persistence.PersistenceManager;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;

import static com.datastax.driver.core.querybuilder.QueryBuilder.*;
import static info.archinnov.achilles.demo.entity.UserTracking.Action.ADD_ITEM_TO_CART;
import static info.archinnov.achilles.demo.referential.SchemaName.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserTrackingTest {

    @Rule
    public AchillesResource resource = AchillesResourceBuilder
            .withEntityClasses(Arrays.<Class<?>>asList(UserTracking.class))
            .withKeyspaceName(KEYSPACE_FOR_TYPE_TRANSFORMER)
            .tablesToTruncate(USER_TRACKING)
            .truncateBeforeAndAfterTest().build();

    private PersistenceManager manager = resource.getPersistenceManager();

    private Session session = resource.getNativeSession();

    private StalkerService service = new StalkerService();

    @Before
    public void setUp() {
        service.manager = this.manager;
    }

    @Test
    public void should_insert_action_for_user() throws Exception {
        //Given
        Date date = new Date();
        Long itemId = RandomUtils.nextLong(0, Long.MAX_VALUE);

        //When
        service.insertUserAction("emc²", date, ADD_ITEM_TO_CART, itemId);

        //Then
        final Row row = session.execute(select().from(USER_TRACKING).where(eq("login", "emc²"))
                .and(eq("date",date))).one();

        assertThat(row).isNotNull();
        assertThat(row.getString("login")).isEqualTo("emc²");
        assertThat(row.getString("action")).isEqualTo(ADD_ITEM_TO_CART.name());
        assertThat(row.getLong("item_id")).isEqualTo(itemId);
    }
}
