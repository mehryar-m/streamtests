package streams.toplogy;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;

public class EntityStatusTopology {

    public static Topology build() {
        StreamsBuilder streamsBuilder = new StreamsBuilder();


        return streamsBuilder.build();
    }
}
