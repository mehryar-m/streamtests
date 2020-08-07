package config;

import io.jaegertracing.internal.samplers.ConstSampler;
import io.opentracing.Tracer;
import io.opentracing.contrib.kafka.streams.TracingKafkaClientSupplier;
import io.opentracing.util.GlobalTracer;

import javax.annotation.PostConstruct;

public class TracerConfig {
    private String jaegerHost;
    private Integer jaegerPort;
    private String applicationName;

    public TracerConfig(String jaegerHost, Integer jaegerPort, String applicationName) {
        this.jaegerHost = jaegerHost;
        this.jaegerPort = jaegerPort;
        this.applicationName = applicationName;
    }


    public Tracer tracer() {
        return io.jaegertracing.Configuration.fromEnv(applicationName)
                .withSampler(
                        io.jaegertracing.Configuration.SamplerConfiguration.fromEnv()
                                .withType(ConstSampler.TYPE)
                                .withParam(1))
                .withReporter(
                        io.jaegertracing.Configuration.ReporterConfiguration.fromEnv()
                                .withLogSpans(true)
                                .withFlushInterval(1000)
                                .withMaxQueueSize(10000)
                                .withSender(
                                        io.jaegertracing.Configuration.SenderConfiguration.fromEnv()
                                                .withAgentHost(jaegerHost)
                                                .withAgentPort(jaegerPort)
                                ))
                .getTracer();
    }

    @PostConstruct
    public void registerToGlobalTracer() {
        if (!GlobalTracer.isRegistered()) {
            GlobalTracer.register(tracer());
        }
    }

    public TracingKafkaClientSupplier getKafkaClientSupplier() {
        return new TracingKafkaClientSupplier(tracer());
    }


}
