package com.bjit.PaymentApp.config;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("order-app")
public interface FeignClientsConfig {

}
