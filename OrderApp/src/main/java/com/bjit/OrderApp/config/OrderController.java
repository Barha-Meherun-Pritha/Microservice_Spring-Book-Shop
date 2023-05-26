package com.bjit.OrderApp.config;

import com.bjit.OrderApp.entity.OrderEntity;
import com.bjit.OrderApp.model.OrderRequestModel;
import com.bjit.OrderApp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    Logger logger = Logger.getLogger("OrderController");

    @Autowired
    BookFeignClientsConfig bookFeignClientsConfig;

    @Autowired
    PaymentFeignClientsConfig paymentFeignClientsConfig;

    @GetMapping("price/{id}")
    public String price(@PathVariable String id){
        Long price = bookFeignClientsConfig.price(id);
        logger.info("Book Id: " + id + ", Price: " + price);
        return "Book Id: " + id + ", Price: " + price;
    }

    @GetMapping("inventory/{id}")
    public String inventory(@PathVariable String id){
        Long stock = bookFeignClientsConfig.inventory(id);
        logger.info("Book Id: " + id + ", Inventory: " + stock);
        return "Book Id: " + id + ", Inventory: " + stock;
    }

    @GetMapping("getPrice/{id}")
    public String getPrice(@PathVariable String id){
        Long price = bookFeignClientsConfig.getPrice(id);
        logger.info("Book Id: " + id + ", Price: " + price);
        return "Book Id: " + id + ", Price: " + price;
    }

    @GetMapping("getInventory/{id}")
    public String getInventory(@PathVariable String id){
        Long stock = bookFeignClientsConfig.getInventory(id);
        logger.info("Book Id: " + id + ", Inventory: " + stock);
        return "Book Id: " + id + ", Inventory: " + stock;
    }

    @GetMapping("/id/{OrderId}")
    public ResponseEntity<Object> getOrder(@PathVariable String OrderId) {
        return orderService.getOrder(Long.parseLong(OrderId));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> newOrder(@RequestBody OrderRequestModel Order) {
        return orderService.createOrder(Order);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable String id, @RequestBody OrderRequestModel Order) {
        return orderService.updateOrder(Long.parseLong(id), Order);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable String id) {
        return orderService.deleteOrder(Long.parseLong(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<OrderEntity>> allOrder() {
        return orderService.getAllOrders();
    }

    @GetMapping("/payment/{pay}")
    public ResponseEntity<Iterable<OrderEntity>> getOrderByPayment(@PathVariable String pay) {
        return orderService.getOrderByPayment(Long.parseLong(pay));
    }

}
