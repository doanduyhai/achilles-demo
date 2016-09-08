package info.archinnov.achilles.demo.b_simple_usage;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;

import info.archinnov.achilles.demo.a_bean_mapping.EmailMessage;
import info.archinnov.achilles.generated.manager.EmailMessage_Manager;
import info.archinnov.achilles.type.Empty;

public class e_AsyncSupport {

    @Inject
    EmailMessage_Manager emailManager;

    public void demo() {

        final EmailMessage instance = new EmailMessage();

        // Simple insert
        final CompletableFuture<Empty> empty = emailManager
                .crud()
                .insert(instance)
                .ifNotExists()
                .executeAsync();

        // Simple select
        final CompletableFuture<EmailMessage> asyncResult = emailManager
                .crud()
                .findById("userLogin", "inboxType", new UUID(0, 0))
                .withResultSetAsyncListener(rs -> {
                    /**
                     * Inspect resultSet asynchronously
                     * before passing to Achilles for processing
                     *
                     * Methods that trigger reading of data
                     * like all() or fetchMoreResults()
                     * are forbidden
                     */
                    rs.getColumnDefinitions();
                    return rs;
                })
                .withRowAsyncListener(row -> {

                    /**
                     * Inspect the current row asynchronously
                     * It is allowed to READ row data here
                     */
                    row.getColumnDefinitions();
                    return row;
                })
                .getAsync();

    }
}
