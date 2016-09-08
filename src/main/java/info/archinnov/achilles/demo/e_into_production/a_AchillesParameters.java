package info.archinnov.achilles.demo.e_into_production;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import javax.validation.Validator;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.Session;
import com.fasterxml.jackson.databind.ObjectMapper;

import info.archinnov.achilles.configuration.ConfigurationParameters;
import info.archinnov.achilles.generated.ManagerFactory;
import info.archinnov.achilles.generated.ManagerFactoryBuilder;
import info.archinnov.achilles.internals.cache.StatementsCache;
import info.archinnov.achilles.type.SchemaNameProvider;
import info.archinnov.achilles.type.interceptor.Interceptor;
import info.archinnov.achilles.type.strategy.InsertStrategy;

public class a_AchillesParameters {

    @Inject
    private Cluster cluster;

    private ManagerFactory factory = ManagerFactoryBuilder
            .builder(cluster)
            .withBeanValidation(true)
            .withBeanValidator((Validator)null)
            .withDefaultExecutorService((ExecutorService) null )
            .withDefaultKeyspaceName("production_ks")
            .withDefaultReadConsistency(ConsistencyLevel.LOCAL_ONE)
            .withDefaultWriteConsistency(ConsistencyLevel.LOCAL_ONE)
            .withExecutorServiceThreadFactory(null)
            .withEventInterceptors(Arrays.<Interceptor<?>>asList())
            .withGlobalInsertStrategy(InsertStrategy.ALL_FIELDS)
            .withJacksonMapper((ObjectMapper)null)
            .withMaxPreparedStatementCacheSize(10000)
            .withNativeSession((Session) null)
            .withParameter(ConfigurationParameters.BEAN_VALIDATION_VALIDATOR, null)
            .withPostLoadBeanValidation(false)
            .withSchemaNameProvider((SchemaNameProvider) null)
            .withStatementsCache((StatementsCache) null)
            .build();

}
