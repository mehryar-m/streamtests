package streams.transformers;

import com.mehryar.streamtests.entity.Entity;
import com.sun.org.apache.xerces.internal.impl.validation.EntityState;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;

public class StatusTransformer implements ValueTransformer<EntityState, Entity> {
    private final String storeName;
    private KeyValueStore stateStore;
    private ProcessorContext context;

    public StatusTransformer(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public void init(ProcessorContext context) {
        this.context = context;
        stateStore = (KeyValueStore) this.context.getStateStore(storeName);
    }

    @Override
    public Entity transform(EntityState value) {
        return null;
    }

    @Override
    public void close() {

    }
}


