package client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mehryar.streamtests.balance.Balance;
import com.mehryar.streamtests.customer.Customer;
import com.mehryar.streamtests.customerRelationship.CustomerRelationship;
import config.TracerConfig;
import constants.Jaeger;
import constants.Topics;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.opentracing.contrib.kafka.TracingKafkaProducer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class MockDataProducer {

    private static final Logger LOG = LoggerFactory.getLogger(MockDataProducer.class);
    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    private static Callback callback;
    private Properties properties;
    private TracerConfig tracerConfig;

    MockDataProducer() {
        this.tracerConfig = new TracerConfig(Jaeger.URL, Jaeger.PORT, "mock-producer");
        this.properties = new Properties();
        this.properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "htpp://localhost:9092");
        this.properties.put(ProducerConfig.CLIENT_ID_CONFIG, "mock-producer");
        this.properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        this.properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        this.properties.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        this.properties.put(AbstractKafkaSchemaSerDeConfig.AUTO_REGISTER_SCHEMAS, true);
//        this.properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, Collections.singleton(TracingProducerInterceptor.class));

    }

    boolean produceCustomer(Customer customer) {
        Producer<String, Customer> producer = new KafkaProducer<>(this.properties);
        TracingKafkaProducer<String, Customer> tracingKafkaProducer =
                new TracingKafkaProducer<String, Customer>(producer, tracerConfig.tracer());
        LOG.debug("created tracingKafkaProducer");
        ProducerRecord<String, Customer> customerRecord =
                new ProducerRecord<>(
                        Topics.CUSTOMER,
                        customer.getAccountNumber(),
                        customer);
        try {
            RecordMetadata metadata = tracingKafkaProducer.send(customerRecord).get();
            LOG.info("Record sent with key " + " to partition " + metadata.partition()
                    + " with offset " + metadata.offset());
            producer.close();
            return true;
        } catch (ExecutionException | InterruptedException e) {
            LOG.error("Error in sending record");
            System.out.println(e);
        }
        return false;
    }

    boolean produceBalance(Balance balance) {
        Producer<String, Balance> producer =
                new KafkaProducer<>(this.properties);
        TracingKafkaProducer<String, Balance> tracingKafkaProducer =
                new TracingKafkaProducer<String, Balance>(producer, tracerConfig.tracer());
        ProducerRecord<String, Balance> balanceRecord =
                new ProducerRecord<>(
                        Topics.BALANCE,
                        balance.getAccountNumber(),
                        balance);

        try {
            RecordMetadata metadata = tracingKafkaProducer.send(balanceRecord).get();
            LOG.info("Record sent with key " + " to partition " + metadata.partition()
                    + " with offset " + metadata.offset());
            producer.close();
            return true;
        } catch (ExecutionException | InterruptedException e) {
            LOG.error("Error in sending record");
            System.out.println(e);
        }
        return false;
    }

    boolean produceCustomerRelationship(CustomerRelationship customerRelationship) {
        Producer<String, CustomerRelationship> producer =
                new KafkaProducer<>(this.properties);
        TracingKafkaProducer<String, CustomerRelationship> tracingKafkaProducer =
                new TracingKafkaProducer<String, CustomerRelationship>(producer, tracerConfig.tracer());
        ProducerRecord<String, CustomerRelationship> customerRelationshipProducerRecord =
                new ProducerRecord<>(
                        Topics.CUSTOMER_RELATIONSHIP,
                        customerRelationship.getAccountNumber(),
                        customerRelationship);

        try {
            RecordMetadata metadata = tracingKafkaProducer.send(customerRelationshipProducerRecord).get();
            LOG.info("Record sent with key " + " to partition " + metadata.partition()
                    + " with offset " + metadata.offset());
            producer.close();
            return true;
        } catch (ExecutionException | InterruptedException e) {
            LOG.error("Error in sending record");
            System.out.println(e);
        }
        return false;
    }


}
