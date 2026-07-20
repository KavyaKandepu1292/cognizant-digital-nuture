# gateway-loadbalancing-service

Spring Cloud Gateway route `lb://catalog-service` load-balanced across
registered `catalog-service` instances using a **custom weighted-random**
strategy (`WeightedRandomLoadBalancer`), instead of the default Round Robin.

To see weighting in action, give each backend instance a `weight` in its own
`application.yml`, e.g.:

```yaml
eureka:
  instance:
    metadata-map:
      weight: 3   # ~3x more likely to be picked than a weight=1 instance
```

Instances with no `weight` metadata default to 1 (uniform random). Every
selection is logged at INFO level so you can watch the distribution.
