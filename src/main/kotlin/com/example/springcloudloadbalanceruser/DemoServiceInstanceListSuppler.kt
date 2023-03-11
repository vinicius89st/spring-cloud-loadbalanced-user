package com.example.springcloudloadbalanceruser

import org.springframework.cloud.client.DefaultServiceInstance
import org.springframework.cloud.client.ServiceInstance
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier
import reactor.core.publisher.Flux

class DemoServiceInstanceListSuppler(s: String) : ServiceInstanceListSupplier {

    private var serviceId: String? = null

    fun DemoServiceInstanceListSuppler(serviceId: String?) {
        this.serviceId = serviceId
    }


    override fun get(): Flux<List<ServiceInstance?>?>? {
        return Flux.just(
            listOf(
                    DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 8090, false),
                    DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 9092, false),
                    DefaultServiceInstance(serviceId + "3", serviceId, "localhost", 9999, false)
                )
        )
    }

    override fun getServiceId(): String? {
        return this.serviceId
    }

}
