package info.archinnov.achilles.demo.b_simple_usage;

import javax.inject.Inject;

import info.archinnov.achilles.demo.a_bean_mapping.EmailMessage;
import info.archinnov.achilles.generated.manager.EmailMessage_Manager;
import info.archinnov.achilles.type.lightweighttransaction.LWTResultListener;

public class d_SupportForLWT {

    @Inject
    EmailMessage_Manager emailManager;

    public void demo() {

        final EmailMessage instance = new EmailMessage();

        // Simple insert
        emailManager
                .crud()
                .insert(instance)
                .ifNotExists()
                .withLwtResultListener(new LWTResultListener() {
                    @Override
                    public void onError(LWTResult lwtResult) {
                        //Process error here
                    }
                })
                .execute();

        // Simple delete
        emailManager
                .crud()
                .delete(instance)
                .ifExists()
                .execute();
    }
}
