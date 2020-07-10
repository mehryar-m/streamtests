//package streams;
//
//import org.apache.kafka.streams.Topology;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import streams.toplogy.AccountPreferenceRepartitionTopology;
//import streams.toplogy.AugmentedCustomerPreferenceTopology;
//
//public class AccountPreferenceRepartition extends AbstractStreams{
//
//        public AccountPreferenceRepartition(){
//            super("account-preference-repartition");
//        }
//        private static final Logger LOG = LoggerFactory.getLogger(streams.AccountPreferenceRepartition.class);
//
//        @Override
//        public Topology getTopology() {
//            LOG.info(AccountPreferenceRepartitionTopology.build().describe().toString());
//            return AccountPreferenceRepartitionTopology.build();
//        }
//}
