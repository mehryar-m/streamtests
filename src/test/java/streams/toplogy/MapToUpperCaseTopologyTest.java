package streams.toplogy;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
// import org.apache.kafka.test.ProcessorTopologyTestDriver;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Properties;

public class MapToUpperCaseTopologyTest {
//    private ProcessorTopologyTestDriver topologyTestDriver;
//
//    @Before
//    public void setUp(){
//        Properties props = new Properties();
//        props.put(StreamsConfig.CLIENT_ID_CONFIG, "FirstZmart-Kafka-Streams-Client");
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "zmart-purchases");
//        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "FirstZmart-Kafka-Streams-App");
//        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(StreamsConfig.REPLICATION_FACTOR_CONFIG, 1);
//        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class);
//
//        StreamsConfig streamsConfig = new StreamsConfig(props);
//        Topology topology = MapToUpperCaseTopology.build();
//
//        topologyTestDriver = new ProcessorTopologyTestDriver(streamsConfig, topology);
//
//    }
//
//    @Test
//    @DisplayName("testing Map to larger")
//    public void testMapToUpperCaseTopologyFlow(){
//        Serde<String> stringSerde = new Serdes.StringSerde();
//        String inputString = "hello";
//        String expectedString = "HELLO";
//
//        topologyTestDriver.process("dm1_topic",
//                "",
//                inputString,
//                stringSerde.serializer(),
//                stringSerde.serializer());
//
//        ProducerRecord<String, String> output = topologyTestDriver.readOutput("dm1_upper",
//                stringSerde.deserializer(), stringSerde.deserializer());
//
//        assertEquals(output.value(), expectedString);
//
//
//    }
//


}
