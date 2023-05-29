package com.bjit.OrderApp.config;

import com.bjit.OrderApp.entity.OrderEntity;
import com.bjit.OrderApp.model.OrderRequestModel;
import com.bjit.OrderApp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.logging.Logger;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    Logger logger = Logger.getLogger("OrderController");

    @Autowired
    BookFeignClientsConfig bookFeignClientsConfig;

    @GetMapping("price/{id}")
    public String price(@PathVariable String id){
        Long price = bookFeignClientsConfig.price(id);
        if(price==null){
            logger.info("No book found!");
            return "No book found!";
        }
        else {
            logger.info("Book Id: " + id + ", Price: " + price);
            return "Book Id: " + id + ", Price: " + price;
        }
    }

    @GetMapping("name/{id}")
    public String name(@PathVariable String id){
        String name = bookFeignClientsConfig.name(id);
        if(Objects.equals(name, "No book found with this ID!")){
            logger.info("No book found!");
            return "No book found!";
        }
        else {
            logger.info("Book Id: " + id + ", Name: " + name);
            return "Book Id: " + id + ", Name: " + name;
        }
    }

    @GetMapping("inventory/{payment}-{id}-{quantity}")
    public String inventory(@PathVariable String payment, @PathVariable String id, @PathVariable String quantity){
        String name = bookFeignClientsConfig.name(id);
        if(Objects.equals(name, "No book found with this ID!")){
            logger.info("No book found!");
            return "No book found!";
        }
        else {
            Long stock = bookFeignClientsConfig.inventory(id, quantity);
            if(stock<Long.parseLong(quantity)) {
                logger.info("Not enough book in the inventory!");
                return "Not enough book in the inventory!";
            }
            else{
                Long price = bookFeignClientsConfig.price(id);
                long total = price * Long.parseLong(quantity);
                Object order = orderService.newOrder(payment, id, quantity, price);
                String msg = "Book Id: " + id + ", Name: " + name + ", Inventory: " + stock + ", Price: " + price + ", Quantity: " + quantity + ", Total: " + total;
                logger.info(msg);
                return msg;
            }
        }
    }

    @GetMapping("/pay/{payment}")
    public Long getPayment(@PathVariable String payment) {
        Long total = orderService.getPayment(Long.parseLong(payment));
        if(total==null) {
            logger.info("No payment found with this ID!");
            return 0L;
        }
        else {
            logger.info("Payment ID: " + payment + ", Total: " + total);
            return total;
        }
    }


    @GetMapping("/id/{OrderId}")
    public ResponseEntity<Object> getOrder(@PathVariable String OrderId) {
        return orderService.getOrder(Long.parseLong(OrderId));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequestModel Order) {
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
