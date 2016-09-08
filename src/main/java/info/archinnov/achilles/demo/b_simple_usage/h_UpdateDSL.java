package info.archinnov.achilles.demo.b_simple_usage;

import java.util.UUID;
import javax.inject.Inject;

import info.archinnov.achilles.demo.d_power_users.Interlocutor;
import info.archinnov.achilles.generated.manager.EmailMessage_Manager;
import info.archinnov.achilles.type.SchemaNameProvider;

public class h_UpdateDSL {

    @Inject
    EmailMessage_Manager emailManager;

    public void demo() {

        SchemaNameProvider schemaNameProvider = null;
        UUID emailId = null;

        /**
         * Type safe update
         *
         *  @Index
         *  @Column
         *  private String subject;
         *
         *  // UDT
         *  @Column
         *  @Frozen
         *  private Interlocutor sender;
         *
         *  // UDT
         *  @EmptyCollectionIfNull
         *  @Column
         *  private List<@Frozen Interlocutor> recipients;
         */
        emailManager
                .dsl()
                .update()
                .fromBaseTable()
                .subject().Set("new Subject")
                .recipients().AppendTo(new Interlocutor())
                .where()
                .userLogin().Eq("johnDoe")
                .inboxType().Eq("MAIN")
                .emailId().Eq(emailId)
                .execute();
    }
}
