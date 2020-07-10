//package streams.toplogy;
//
//import com.mehryar.streamtests.accountpreference.AccountPreference;
//import com.mehryar.streamtests.augmentedcustomerpreference.AugmentedCustomerPreference;
//import com.mehryar.streamtests.customeraccount.CustomerAccount;
//import com.mehryar.streamtests.customerprofile.CustomerProfile;
//import com.mehryar.streamtests.joinedaccountprefcustomeraccount.JoinedAccountPrefCustomerAccount;
//import constants.Kafka;
//import constants.Topics;
//import io.confluent.kafka.serializers.KafkaAvroSerializer;
//import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
//import org.apache.kafka.common.serialization.Serde;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.Topology;
//import org.apache.kafka.streams.kstream.*;
//import org.apache.kafka.streams.processor.internals.InternalTopologyBuilder;
//import streams.joiner.AccountPreferenceCustomerJoiner;
//import java.time.Duration;
//import java.util.Collections;
//import java.util.Map;
//
//
//public class AugmentedCustomerPreferenceTopology {
//
//
//
//
//    private static final SpecificAvroSerde<AccountPreference> accountPreferenceSpecificAvroSerde = new SpecificAvroSerde<>();
//
//    private static final SpecificAvroSerde<CustomerAccount> customerAccountSpecificAvroSerde = new SpecificAvroSerde<>();
//
//    private static final SpecificAvroSerde<CustomerProfile> customerProfileSpecificAvroSerde = new SpecificAvroSerde<>();
//
//    private static final SpecificAvroSerde<AugmentedCustomerPreference> augmentedCustomerPreferenceSpecificAvroSerde = new SpecificAvroSerde<>();
//
//
//    static public Topology build(){
//
//        final Map<String, String> serdeConfig = Collections.singletonMap("schema.registry.url", Kafka.SCHEMA_REGISTRY_URL);
//
//
//        StreamsBuilder streamsBuilder = new StreamsBuilder();
//
//        accountPreferenceSpecificAvroSerde.configure(serdeConfig,true);
//        KStream<String, AccountPreference> accountPreferenceKStream =
//                streamsBuilder.stream(Topics.ACCOUNT_PREFERENCES_TOPIC,
//                        Consumed.with(Serdes.String(),accountPreferenceSpecificAvroSerde));
//
//        customerAccountSpecificAvroSerde.configure(serdeConfig, true);
//        KStream<String, CustomerAccount> customerAccountKStream =
//                streamsBuilder.stream(Topics.CUSTOMER_ACCOUNTS_TOPIC,
//                        Consumed.with(Serdes.String(), customerAccountSpecificAvroSerde));
//
//;
//
//
//        Duration duration = Duration.ofDays(10);
//
//        KStream<String, JoinedAccountPrefCustomerAccount> joinedAccountPrefCustomerAccountKStream =
//                accountPreferenceKStream.join(customerAccountKStream.selectKey((k,v) ->
//                                v.getAccount()),
//                        new AccountPreferenceCustomerJoiner(), JoinWindows.of(duration));
//
//        joinedAccountPrefCustomerAccountKStream.to("temp");
//        //        customerProfileSpecificAvroSerde.configure(serdeConfig, true);
////        KStream<String, CustomerProfile> customerProfileKStream =
////                streamsBuilder.stream(CUSTOMER_PROFILES_TOPIC,
////                        Consumed.with(Serdes.String(),customerProfileSpecificAvroSerde)).through("temp");
//
//        return streamsBuilder.build();
//    }
//
//}
