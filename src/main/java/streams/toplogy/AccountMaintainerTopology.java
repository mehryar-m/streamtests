package streams.toplogy;

import com.mehryar.streamtests.account.Account;
import com.mehryar.streamtests.customer.Customer;
import com.mehryar.streamtests.customerRelationship.CustomerRelationship;
import constants.Kafka;
import constants.Topics;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.glassfish.jersey.internal.inject.Custom;

import java.util.Collections;
import java.util.Map;

/*
 Produces to the Account Topic:
 Account Topic is a complex state that is staying updated through Balance, CustomerRelationship and Customer
 */
public class AccountMaintainerTopology {

    private static SpecificAvroSerde<Account> accountSpecificAvroSerde = new SpecificAvroSerde<>();
    private static SpecificAvroSerde<Customer> customerSpecificAvroSerde = new SpecificAvroSerde<>();
    private static SpecificAvroSerde<CustomerRelationship> customerReleationshipSpecificAvroSerde = new SpecificAvroSerde<>();

    public static Topology build(){
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        configureSerdes();


        KStream<String, CustomerRelationship> customerRelationshipKStream =
                streamsBuilder.stream(Topics.CUSTOMER_RELATIONSHIP,
                        Consumed.with(Serdes.String(), customerReleationshipSpecificAvroSerde));
        KTable<String, Account> relationshipTable = customerRelationshipKStream.map(
                (KeyValueMapper<String, CustomerRelationship, KeyValue<String, Account>>) (key, value) -> {
                    Account account = new Account();
                    account.setKeyId(value.getKeyId());
                    account.setAccountNumber(value.getAccountNumber());
                    account.setName("default");
                    account.setBalance(0);
                    return new KeyValue<>(value.getAccountNumber(), account);
                }).toTable();


        KStream<String, Customer> customerKStream =
                streamsBuilder.stream(Topics.CUSTOMER, Consumed.with(Serdes.String(), customerSpecificAvroSerde));
        customerKStream.leftJoin(relationshipTable, (value1, value2) -> {
            Account account = new Account();
            account.setName(value1.getAccountNumber());
            account.setAccountNumber(value1.getAccountNumber());
            if (value2 != null) {
                account.setKeyId(value2.getKeyId());
            } else {
                account.setKeyId("default");
            }
            account.setBalance(0);
            return account;
        }).to(Topics.ACCOUNT);

        return streamsBuilder.build();

    }

    private static void configureSerdes() {

        final Map<String, String> serdeConfig = Collections.singletonMap("schema.registry.url", Kafka.SCHEMA_REGISTRY_URL);
        accountSpecificAvroSerde.configure(serdeConfig, false);
        customerSpecificAvroSerde.configure(serdeConfig, false);
        customerReleationshipSpecificAvroSerde.configure(serdeConfig, false);
    }
}
