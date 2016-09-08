package info.archinnov.achilles.config;

import info.archinnov.achilles.annotations.CompileTimeConfig;
import info.archinnov.achilles.type.CassandraVersion;

@CompileTimeConfig(cassandraVersion = CassandraVersion.CASSANDRA_2_2_X)
public interface AchillesConfig {
}
