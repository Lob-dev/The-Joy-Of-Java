# Sample-Spring-Cloud-Api-Gateway
Sample Spring-Cloud-Api-Gateway Project of Spring Boot

<br />
<br />

## Proejct Stack
- Spring Webflux
- Spring Cloud Gateway
- Spring Data Redis reactive
- Spring Boot Actuator
- micrometer-registry-prometheus
- configuration-processor
- Java 17
- Fast API

<br />
<br />

## Project Feature
모든 기능은 Java Config 기반으로 구현
- Spring Cloud Gateway RouteLocator
- Spring Cloud Gateway RateLimiter
  - use Reactive Redis
- Spring WebFlux RouterFunction
  - use Web Client
- Spring Boot Actuator + Prometheus
  - API Gateway Metric 추가
  - 2.2 이전까지 기본 제공되던 HttpTrace metric을 활성화 하는 방법 추가
- Java 17 문법 사용
