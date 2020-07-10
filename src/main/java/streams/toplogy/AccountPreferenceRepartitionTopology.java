//package streams.toplogy;
//
//import com.mehryar.streamtests.accountpreference.AccountPreference;
//import constants.Kafka;
//import constants.Topics;
//import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.Topology;
//import org.apache.kafka.streams.kstream.Consumed;
//import org.apache.kafka.streams.kstream.KStream;
//import org.apache.kafka.streams.kstream.Produced;
//
//import java.util.Collections;
//import java.util.Map;
//
//public class AccountPreferenceRepartitionTopology {
//
//    private static final SpecificAvroSerde<AccountPreference> accountPreferenceSpecificAvroSerde = new SpecificAvroSerde<>();
//    private static final Map<String, String> serdeConfig =
//            Collections.singletonMap("schema.registry.url", Kafka.SCHEMA_REGISTRY_URL);
//
//    public static Topology build() {
//        StreamsBuilder streamsBuilder = new StreamsBuilder();
//        accountPreferenceSpecificAvroSerde.configure(serdeConfig, true);
//        KStream<String, AccountPreference> accountPreferenceKStream =
//                streamsBuilder.stream(Topics.ACCOUNT_PREFERENCES_TOPIC,
//                        Consumed.with(Serdes.String(), accountPreferenceSpecificAvroSerde));
//
//        accountPreferenceKStream.selectKey((
//                (key, value) -> value.getAccountPreference())).
//                through("temp", Produced.with(Serdes.String(), accountPreferenceSpecificAvroSerde));
//
//        return streamsBuilder.build();
//    }
//
//}
