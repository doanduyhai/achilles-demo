package info.archinnov.achilles.demo.c_data_types_and_codecs;

import org.joda.time.DateTime;

import info.archinnov.achilles.annotations.Codec;
import info.archinnov.achilles.annotations.CodecRegistry;
import info.archinnov.achilles.annotations.Enumerated;

@CodecRegistry
public class g_AnotherCodecRegistry {

    /**
     * Duplicate codec declaration
     * Will trigger error at compile time
     */
    //@Codec(JodaTimeToLong.class)
    //private DateTime dateTime;

    @Enumerated
    private InboxType inboxType;
}
