package com.person.springcloud.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DcController {
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/hello")
	public Map hello() {
		Map result = new HashMap();
		result.put("errCode", "S");
		result.put("errMsg", null);
		return result;
	}
	
	/**
	 * 先通过loadBalancerClient的choose函数来负载均衡的选出一个eureka-client的服务实例，这个服务实例的基本信息存储在ServiceInstance中，然后通过这些对象中的信息拼接出访问/dc接口的详细地址，最后再利用RestTemplate对象实现对服务提供者接口的调用。
	 * @return
	 */
	@GetMapping("/consumer")
	public String dc() {
		return restTemplate.getForObject("http://eureka-client/dc", String.class);
	}
}
