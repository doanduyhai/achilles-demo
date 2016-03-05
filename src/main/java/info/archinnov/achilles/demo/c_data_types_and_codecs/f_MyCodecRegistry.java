package info.archinnov.achilles.demo.c_data_types_and_codecs;

import org.joda.time.DateTime;

import info.archinnov.achilles.annotations.Codec;
import info.archinnov.achilles.annotations.CodecRegistry;

/**
 * You can have MANY codec registry classes
 * Just annotate them with @CodecRegistry
 *
 * Duplicate codec declaration between different @CodecRegistry is forbidden
 */
@CodecRegistry
public class f_MyCodecRegistry {

    /**
     * INLINE codec declaration will have
     * HIGHER priority than codecs declared
     * in Registry
     */
    @Codec(JodaTimeToLong.class)
    private DateTime dateTime;
}
