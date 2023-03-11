package com.example.springcloudloadbalanceruser
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

class SayHelloConfiguration {

    @Bean
    @Primary
    fun serviceInstanceListSupplier(): ServiceInstanceListSupplier? {
        return DemoServiceInstanceListSuppler("say-hello")
    }
}