package info.archinnov.achilles.demo.c_data_types_and_codecs;

import java.util.UUID;

import com.datastax.driver.core.TupleValue;

import info.archinnov.achilles.type.tuples.Tuple1;
import info.archinnov.achilles.type.tuples.Tuple10;
import info.archinnov.achilles.type.tuples.Tuple2;

public class a_TuplesUsage {

    private Tuple1<String> tuple1;

    private Tuple2<String, Integer> tuple2;

    //etc ...

    private Tuple10<Integer, String, UUID, Integer, Integer,
                    UUID, String, Integer, Long, Double> tuple10;

    // this is also supported
    private TupleValue nativeTupleType;
}
