package streams;

import org.apache.kafka.streams.Topology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.toplogy.AccountMaintainerTopology;

public class AccountMaintainer extends AbstractStreams{
    public AccountMaintainer(){
        super("account-maintainer");
    }
    private static final Logger LOG = LoggerFactory.getLogger(streams.AccountMaintainer.class);

    @Override
    public Topology getTopology() {
        LOG.info(AccountMaintainerTopology.build().describe().toString());
        return AccountMaintainerTopology.build();
    }
}
