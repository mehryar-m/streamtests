//package streams;
//
//import org.apache.kafka.streams.Topology;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import streams.toplogy.AugmentedCustomerPreferenceTopology;
//
//
//public class AugmentedCustomerPreference extends AbstractStreams {
//
//    public AugmentedCustomerPreference(){
//        super("map-to-uppercase");
//    }
//    private static final Logger LOG = LoggerFactory.getLogger(AugmentedCustomerPreference.class);
//
//    @Override
//    public Topology getTopology() {
//        LOG.info(AugmentedCustomerPreferenceTopology.build().describe().toString());
//        return AugmentedCustomerPreferenceTopology.build();
//    }
//}
