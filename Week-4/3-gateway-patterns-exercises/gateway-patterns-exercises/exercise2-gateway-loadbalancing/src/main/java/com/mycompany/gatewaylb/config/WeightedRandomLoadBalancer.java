package com.mycompany.gatewaylb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.SelectedInstanceCallback;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Custom load-balancing strategy that replaces the default Round Robin.
 *
 * Rather than plain uniform random selection, each instance can advertise a
 * "weight" via its own metadata (metadata-map.weight=N in that instance's
 * application.yml when registered with a discovery server). Instances with a
 * higher weight are proportionally more likely to be chosen — e.g. a beefier
 * instance can be given weight=3 to receive roughly 3x the traffic of a
 * weight=1 instance. Instances with no weight metadata default to weight=1
 * (equivalent to plain uniform random). Every selection is logged so the
 * distribution can be observed in practice.
 */
public class WeightedRandomLoadBalancer implements ReactorServiceInstanceLoadBalancer {

    private static final Logger log = LoggerFactory.getLogger(WeightedRandomLoadBalancer.class);
    private static final String WEIGHT_METADATA_KEY = "weight";
    private static final int DEFAULT_WEIGHT = 1;

    private final ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;
    private final String serviceId;

    public WeightedRandomLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider,
                                       String serviceId) {
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
        this.serviceId = serviceId;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider
                .getIfAvailable(NoopServiceInstanceListSupplier::new);

        return supplier.get(request).next()
                .map(instances -> pickWeightedInstance(supplier, instances));
    }

    private Response<ServiceInstance> pickWeightedInstance(ServiceInstanceListSupplier supplier,
                                                             List<ServiceInstance> instances) {
        if (instances.isEmpty()) {
            log.warn("No instances available for service '{}'", serviceId);
            return new EmptyResponse();
        }

        int totalWeight = instances.stream().mapToInt(this::weightOf).sum();
        int roll = ThreadLocalRandom.current().nextInt(totalWeight);

        ServiceInstance chosen = instances.get(0);
        int cumulative = 0;
        for (ServiceInstance instance : instances) {
            cumulative += weightOf(instance);
            if (roll < cumulative) {
                chosen = instance;
                break;
            }
        }

        log.info("Load balancer picked instance {}:{} for service '{}' (weight={}, roll={}/{})",
                chosen.getHost(), chosen.getPort(), serviceId, weightOf(chosen), roll, totalWeight);

        Response<ServiceInstance> response = new DefaultResponse(chosen);
        if (supplier instanceof SelectedInstanceCallback callback) {
            callback.selectedServiceInstance(chosen);
        }
        return response;
    }

    private int weightOf(ServiceInstance instance) {
        String weightValue = instance.getMetadata().get(WEIGHT_METADATA_KEY);
        if (weightValue == null) {
            return DEFAULT_WEIGHT;
        }
        try {
            int weight = Integer.parseInt(weightValue);
            return weight > 0 ? weight : DEFAULT_WEIGHT;
        } catch (NumberFormatException e) {
            return DEFAULT_WEIGHT;
        }
    }
}
