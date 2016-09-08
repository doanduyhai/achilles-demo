package info.archinnov.achilles.demo.d_power_users;

import java.util.List;
import javax.inject.Inject;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ExecutionInfo;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import info.archinnov.achilles.demo.a_bean_mapping.EmailMessage;
import info.archinnov.achilles.generated.manager.EmailMessage_Manager;
import info.archinnov.achilles.internals.dsl.crud.InsertWithOptions;

public class g_NativeStatements {

    @Inject
    EmailMessage_Manager emailManager;

    public void demo() {

        final EmailMessage instance = new EmailMessage();

        final InsertWithOptions<EmailMessage> insert = emailManager
                .crud()
                .insert(instance)
                .ifNotExists();

        /**
         * Bound values extracted from instance objects
         */
        final List<Object> boundValues = insert.getBoundValues();

        /**
         * Encoded bound values using Achilles Codec system
         */
        final List<Object> encodedBoundValues = insert.getEncodedBoundValues();

        /**
         * Plain string statement
         */
        final String statementAsString = insert.getStatementAsString();

        /**
         * Access to native Session object
         */
        final Session nativeSession = emailManager.getNativeSession();

        /**
         * 1. Generate the prepared statement OR get it from the cache
         * 2. Extract bound values from instance object
         * 3. Encode bound values to CQL-compatible types
         * 4. Generate bound statement
         */
        final BoundStatement boundStatement = insert.generateAndGetBoundStatement();
        nativeSession.execute(boundStatement);

        /**
         * Plain mapper feature
         */
        Row row = nativeSession.execute("SELECT ...").one();

        final EmailMessage emailMessage = emailManager.mapFromRow(row);

        /**
         * Execute and retrieve the ExecutionInfo object
         */
        final ExecutionInfo executionInfo = insert.executeWithStats();

        executionInfo.getPagingState();
        executionInfo.getAchievedConsistencyLevel();
        executionInfo.getQueryTrace();
        executionInfo.getQueriedHost();
        executionInfo.isSchemaInAgreement();


    }
}
