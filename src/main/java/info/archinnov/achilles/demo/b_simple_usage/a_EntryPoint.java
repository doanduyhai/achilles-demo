package info.archinnov.achilles.demo.b_simple_usage;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;

import info.archinnov.achilles.generated.ManagerFactory;
import info.archinnov.achilles.generated.ManagerFactoryBuilder;
import info.archinnov.achilles.generated.manager.EmailMessage_Manager;

public class a_EntryPoint {

    @Inject
    private Cluster cluster;

    private ManagerFactory managerFactory;

    @PostConstruct
    public void init() {

        this.managerFactory = ManagerFactoryBuilder
                .builder(cluster)
                .withBeanValidation(true)
                .withDefaultReadConsistency(ConsistencyLevel.LOCAL_ONE)
                .build();
        
        final EmailMessage_Manager emailManager = managerFactory.forEmailMessage();

    }



}
