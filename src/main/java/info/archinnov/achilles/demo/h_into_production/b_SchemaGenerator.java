package info.archinnov.achilles.demo.h_into_production;

import java.io.File;
import java.io.IOException;

import info.archinnov.achilles.schema.SchemaGenerator;

public class b_SchemaGenerator {

    public static void main(String ... args) throws IOException {
        final File file = new File("/tmp/schema.cql");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();

        SchemaGenerator
                .builder()
                .withKeyspace("production")
                .generateTo(file);
    }

    /**
     * Possible command line usage to generate schema:
     *
     * java -jar achilles-schema-generator-<VERSION>-shaded.jar
     *
     */
}
