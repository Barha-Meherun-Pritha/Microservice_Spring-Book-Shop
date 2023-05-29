package com.bjit.PaymentApp.config;

import com.bjit.PaymentApp.entity.PaymentEntity;
import com.bjit.PaymentApp.model.PaymentRequestModel;
import com.bjit.PaymentApp.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    Logger logger = Logger.getLogger("PaymentController");

    @Autowired
    FeignClientsConfig feignClientsConfig;

    @GetMapping("pay/{id}-{type}")
    public String getPayment(@PathVariable String id, @PathVariable String type){
        Long total = feignClientsConfig.getPayment(id);
        if(total==0L) {
            logger.info("No payment found with this ID!");
            return "No payment found with this ID!";
        }
        else {
            paymentService.newPayment(id, total, type);
            logger.info("Payment ID: " + id + ", Total Payment: " + total + ", Payment Type: " + type);
            return "Payment ID: " + id + ", Total Payment: " + total + ", Payment Type: " + type;
        }
    }

    @GetMapping("/id/{PaymentId}")
    public ResponseEntity<Object> getPayment(@PathVariable String PaymentId) {
        return paymentService.getPayment(Long.parseLong(PaymentId));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> newPayment(@RequestBody PaymentRequestModel Payment) {
        return paymentService.createPayment(Payment);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updatePayment(@PathVariable String id, @RequestBody PaymentRequestModel Payment) {
        return paymentService.updatePayment(Long.parseLong(id), Payment);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePayment(@PathVariable String id) {
        return paymentService.deletePayment(Long.parseLong(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<PaymentEntity>> allPayment() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<Iterable<PaymentEntity>> getPaymentByPayment(@PathVariable String type) {
        return paymentService.getPaymentByPaymentType(type);
    }

}
