package com.bjit.OrderApp.config;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("payment-app")
public interface PaymentFeignClientsConfig {

}
