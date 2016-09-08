package info.archinnov.achilles.demo.c_advanced_usage;

import javax.inject.Inject;

import info.archinnov.achilles.generated.manager.EmailMessage_Manager;

public class d_JSONFeature {


    @Inject
    EmailMessage_Manager manager;

    public void JSON_Feature() {
        //InsertAsJson
        manager
                .crud()
                ;

        //Select JSON *
        manager
                .dsl()
                .select()
                ;

        //WHERE xxx = fromJson(?)
        manager
                .dsl()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .userLogin().Eq_FromJson("");

        //UPDATE ... SET xxx = fromJson(?)
        manager
                .dsl()
                .update()
                .fromBaseTable()
                .body();

        // LWT with fromJson()
        // UPDATE .... SET .... WHERE ... IF xxx = fromJson(?)
        manager
                .dsl()
                .update()
                .fromBaseTable()
                .body().Set("")
                .where()
                .userLogin().Eq(null)
                .inboxType().Eq(null)
                .emailId().Eq(null)
                .if_Body();

    }
}
