package info.archinnov.achilles.demo.d_power_users;

import info.archinnov.achilles.annotations.CodecRegistry;
import info.archinnov.achilles.annotations.Enumerated;

@CodecRegistry
public class f_AnotherCodecRegistry {

    /**
     * Duplicate codec declaration
     * Will trigger error at compile time
     */
    //@Codec(JodaTimeToLong.class)
    //private DateTime dateTime;

    @Enumerated
    private InboxType inboxType;
}
