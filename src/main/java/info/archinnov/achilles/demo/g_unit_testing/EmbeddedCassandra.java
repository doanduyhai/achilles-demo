package info.archinnov.achilles.demo.g_unit_testing;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import info.archinnov.achilles.embedded.CassandraEmbeddedServer;
import info.archinnov.achilles.embedded.CassandraEmbeddedServerBuilder;

public class EmbeddedCassandra {

    public static void main(String ... args) {

        final CassandraEmbeddedServer server = CassandraEmbeddedServerBuilder
                .builder()
                .cleanDataFilesAtStartup(true)
                .withClusterName("My Cluster")
                .withKeyspaceName("test_keyspace")
                .withDurableWrite(false)
                .withScript("startup_script.cql")
//                .withCommitLogFolder("")
//                .withDataFolder("")
//                .withCQLPort(123)
//                .withThriftPort(456)
                .buildServer();

        /**
         * Create a new Cluster object
         */
        final Cluster cluster = server.getNativeCluster();

        /**
         * Create a new session and connect it to the
         * keyspace defined by .withKeyspaceName("...")
         */
        final Session session = server.getNativeSession();
    }
}
