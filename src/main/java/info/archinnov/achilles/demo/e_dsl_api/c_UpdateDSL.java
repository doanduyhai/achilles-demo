package info.archinnov.achilles.demo.e_dsl_api;

import java.util.UUID;
import javax.inject.Inject;

import info.archinnov.achilles.demo.c_data_types_and_codecs.InboxType;
import info.archinnov.achilles.demo.c_data_types_and_codecs.Interlocutor;
import info.archinnov.achilles.generated.manager.EmailMessage_Manager;
import info.archinnov.achilles.generated.manager.UnreadEmailCount_Manager;
import info.archinnov.achilles.type.SchemaNameProvider;

public class c_UpdateDSL {

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
                .subject_Set("new Subject")
                .recipients_AppendTo(new Interlocutor())
                .where()
                .userLogin_Eq("johnDoe")
                .inboxType_Eq("MAIN")
                .emailId_Eq(emailId)
                .execute();
    }
}
