package info.archinnov.achilles.demo.d_core_api;

import java.util.UUID;
import javax.inject.Inject;

import info.archinnov.achilles.demo.c_data_types_and_codecs.EmailMessage;
import info.archinnov.achilles.generated.manager.EmailMessage_Manager;

public class b_CoreAPI {

    @Inject
    EmailMessage_Manager emailManager;

    public void demo() {

        final EmailMessage instance = new EmailMessage();

        // Simple insert
        emailManager
                .crud()
                .insert(instance)
                .execute();

        // Simple delete
        emailManager
                .crud()
                .delete(instance)
                .execute();

        // Delete by Id
        emailManager
                .crud()
                .deleteById("userLogin", "inboxType", new UUID(0,0))
                .execute();

        // Find by Id
        final EmailMessage foundEmail = emailManager
                .crud()
                .findById("userLogin", "inboxType", new UUID(0, 0))
                .get();
    }
}
