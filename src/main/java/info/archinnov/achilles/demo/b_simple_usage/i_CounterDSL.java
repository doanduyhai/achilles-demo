package info.archinnov.achilles.demo.b_simple_usage;

import javax.inject.Inject;

import info.archinnov.achilles.demo.d_power_users.InboxType;
import info.archinnov.achilles.generated.manager.UnreadEmailCount_Manager;

public class i_CounterDSL {

    @Inject
    UnreadEmailCount_Manager unreadEmailCountManager;

    public void demo() {

        /**
         * Look at UnreadEmailCount class first
         */

        /**
         * Restrict Query DSL to UPDATE only
         */
        unreadEmailCountManager
                .dsl()
                .update()
                .fromBaseTable()
                .unreadCount().Decr(10L)
                .totalUnreadCount().Decr(10L)
                .where()
                .userLogin().Eq("johnDoe")
                .inboxType().Eq(InboxType.MAIN)
                .execute();

        /**
         * Update static columns only
         *
         *     @PartitionKey
         *     private String userLogin;
         *
         *     @Static
         *     @Counter
         *     @Column
         *     private Long totalUnreadCount;
         *
         *     @ClusteringColumn
         *     private @Enumerated InboxType inboxType; // inboxType int
         *
         *     @Counter
         *     @Column
         *     private Long unreadCount;
         */
        unreadEmailCountManager
                .dsl()
                .updateStatic()
                .fromBaseTable()
                .totalUnreadCount().Incr(10L)
                .where()
                .userLogin().Eq("login")
                .execute();

    }
}
