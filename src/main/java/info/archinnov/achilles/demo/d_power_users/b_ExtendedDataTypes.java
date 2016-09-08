package info.archinnov.achilles.demo.d_power_users;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Optional;

import com.datastax.driver.core.TupleValue;
import com.datastax.driver.core.UDTValue;

public class b_ExtendedDataTypes {

    // Will be converted to ByteBuffer
    private byte[] byteArray; // --> blob

    private double[] doubles; // --> frozen<list<double>>
    private float[] floats; // --> frozen<list<float>>
    private int[] ints; // --> frozen<list<int>>
    private long[] longs; // --> frozen<list<bigint>>

    private UDTValue nativeUDTType;
    private TupleValue nativeTupleType;

    //JDK 8 dates & times
    private Instant instant;  // --> timestamp
    private LocalDate localDate; // --> date
    private LocalTime localTime; // --> time
    private ZonedDateTime zonedDateTime; // --> tuple<timestamp, varchar>

    //JDK 8 Optional
    private Optional<String> optional; // --> text

}
