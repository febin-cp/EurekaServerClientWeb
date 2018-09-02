package com.training;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class GreetClient {
	@Autowired
	LoadBalancerClient discovery;
	
	
	@RequestMapping("/client")
	public String makeRequest() {
		
		
		ServiceInstance serviceInstance = discovery.choose("webclient");
		URI uri = serviceInstance.getUri();
		
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(uri+"/hello", String.class);
		return response;
		
	}

}
