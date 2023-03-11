package com.example.springcloudloadbalanceruser

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono


@SpringBootApplication
@RestController
class SpringCloudLoadbalancerUserApplication

private var loadBalancedWebClientBuilder: WebClient.Builder? = null
private var lbFunction: ReactorLoadBalancerExchangeFilterFunction? = null


fun SpringCloudLoadbalancerUserApplication(
    webClientBuilder: WebClient.Builder,
    loadBalanceFunction: ReactorLoadBalancerExchangeFilterFunction
) {
    loadBalancedWebClientBuilder = webClientBuilder
    lbFunction = loadBalanceFunction
}

fun main(args: Array<String>) {
    runApplication<SpringCloudLoadbalancerUserApplication>(*args)
}

@RequestMapping("/hi")
fun hi(@RequestParam(value = "name", defaultValue = "Mary") name: String?): Mono<String>? {
    return loadBalancedWebClientBuilder!!.build().get().uri("http://say-hello/greeting")
        .retrieve().bodyToMono(String::class.java)
        .map { greeting: String? ->
            String.format(
                "%s, %s!",
                greeting,
                name
            )
        }
}

@RequestMapping("/hello")
fun hello(@RequestParam(value = "name", defaultValue = "John") name: String?): Mono<String>? {
    return WebClient.builder()
        .filter(lbFunction!!)
        .build().get().uri("http://say-hello/greeting")
        .retrieve().bodyToMono(String::class.java)
        .map { greeting: String? ->
            String.format(
                "%s, %s!",
                greeting,
                name
            )
        }
}
