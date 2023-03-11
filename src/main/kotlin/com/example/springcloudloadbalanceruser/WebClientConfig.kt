package com.example.springcloudloadbalanceruser

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@LoadBalancerClient(name = "say-hello")
public class WebClientConfig {

    @LoadBalanced
    @Bean
    fun webClientBuilder(): WebClient.Builder? {
        return WebClient.builder()
    }
}