package com.bjit.OrderApp.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("book-app")
public interface BookFeignClientsConfig {

    @GetMapping("price/{bookId}")
    public Long price(@PathVariable String bookId);

    @GetMapping("inventory/{bookId}")
    public Long inventory(@PathVariable String bookId);

    @GetMapping("getPrice")
    public Long getPrice(String bookId);

    @GetMapping("/getInventory")
    public Long getInventory(String bookId);

}
