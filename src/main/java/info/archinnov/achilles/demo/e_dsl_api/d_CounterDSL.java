package info.archinnov.achilles.demo.e_dsl_api;

import javax.inject.Inject;

import info.archinnov.achilles.demo.c_data_types_and_codecs.InboxType;
import info.archinnov.achilles.generated.manager.UnreadEmailCount_Manager;

public class d_CounterDSL {

    @Inject
    UnreadEmailCount_Manager unreadEmailCountManager;

    public void demo() {

        /**
         * Restrict Query DSL to UPDATE only
         */
        unreadEmailCountManager
                .dsl()
                .update()
                .fromBaseTable()
                .unreadCount_Decr(10L)
                .totalUnreadCount_Decr(10L)
                .where()
                .userLogin_Eq("johnDoe")
                .inboxType_Eq(InboxType.MAIN)
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
                .totalUnreadCount_Incr(10L)
                .where()
                .userLogin_Eq("login")
                .execute();

    }
}
