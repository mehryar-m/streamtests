package streams.toplogy;


import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Time;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.integration.utils.EmbeddedKafkaCluster;
import org.junit.BeforeClass;
import org.junit.ClassRule;

import java.util.Properties;


public class MapToUpperCaseIntegrationTest {

    private static final int NUM_BROKERS = 1;
    private static final String STRING_SERDE_CLASS = Serdes.String().getClass().getName();
    private final Time mockTime = Time.SYSTEM;

    private KafkaStreams kafkaStreams;
    private StreamsConfig streamsConfig;
    private Properties producerConfig;
    private Properties consumerConfig;

    private static final String DM1_TOPIC = "dm1_topic";
    private static final String DM1_UPPERCASE = "dm1_upper";


}
