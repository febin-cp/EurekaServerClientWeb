import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ServerClientClass {

    @Autowired
    @LoadBalanced
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getProducerFallback")
    public ProducerResponse getValue() {
        return restTemplate.getForObject("http://producer", ProducerResponse.class);
    }

    public ProducerResponse getProducerFallback() {
        ProducerResponse response = new ProducerResponse();
        response.setValue(-1);
        response.setServerPort(-1);
        return response;
    }
}