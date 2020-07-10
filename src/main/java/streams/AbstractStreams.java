package streams;

import config.TracerConfig;
import constants.Jaeger;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import io.opentracing.contrib.kafka.TracingConsumerInterceptor;
import io.opentracing.contrib.kafka.TracingProducerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaClientSupplier;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;

import java.util.Collections;
import java.util.Properties;

public abstract class AbstractStreams {

    private Properties properties;
    private KafkaClientSupplier kafkaClientSupplier;

    AbstractStreams(String application_id){
        this.properties = setProperties(application_id);
        addTracer(application_id);
    }

    public abstract Topology getTopology();

    public void start(){
        KafkaStreams streams = new KafkaStreams(this.getTopology(), properties, kafkaClientSupplier);
        streams.start();
    }

    private void addTracer(String application_id) {
        TracerConfig tracerConfig = new TracerConfig(Jaeger.URL, Jaeger.PORT, application_id);
        this.kafkaClientSupplier = tracerConfig.getKafkaClientSupplier();
    }

    private Properties setProperties(String application_id){
        Properties properties = new Properties();

        properties.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, application_id);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
        properties.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG,
                LogAndContinueExceptionHandler.class);

        properties.put(ConsumerConfig.CLIENT_RACK_CONFIG, "rack-a");
        return properties;
    }


}
