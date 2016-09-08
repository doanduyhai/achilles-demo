package info.archinnov.achilles.demo.b_simple_usage;

import java.nio.ByteBuffer;
import java.util.HashMap;
import javax.inject.Inject;

import com.datastax.driver.core.ConsistencyLevel;

import info.archinnov.achilles.demo.a_bean_mapping.EmailMessage;
import info.archinnov.achilles.generated.manager.EmailMessage_Manager;
import info.archinnov.achilles.type.SchemaNameProvider;
import info.archinnov.achilles.type.strategy.InsertStrategy;

public class c_RuntimeQueryOptions {

    @Inject
    EmailMessage_Manager emailManager;

    public void demo() {

        final EmailMessage instance = new EmailMessage();

        // Simple insert
        emailManager
                .crud()
                .insert(instance)
                .withConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM)
                .withSerialConsistencyLevel(ConsistencyLevel.LOCAL_SERIAL)
                .withInsertStrategy(InsertStrategy.NOT_NULL_FIELDS)
                .withRetryPolicy(null)
                .withTracing()
                .usingTimestamp(10000L)
                .usingTimeToLive(3600)
                .isIdempotent()
                .withOutgoingPayload(new HashMap<String, ByteBuffer>())
                // Multi tenant feature
                .withSchemaNameProvider(new SchemaNameProvider() {
                    @Override
                    public <T> String keyspaceFor(Class<T> entityClass) {
                        // look up a runtime repo for Keyspace name
                        // base on runtime info (userLogin, security token, ...)
                        return "";
                    }

                    @Override
                    public <T> String tableNameFor(Class<T> entityClass) {
                        return "";
                    }
                })
                .execute();
    }
}
