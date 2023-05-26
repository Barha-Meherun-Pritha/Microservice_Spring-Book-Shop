package com.bjit.PaymentApp.config;

import com.bjit.PaymentApp.entity.PaymentEntity;
import com.bjit.PaymentApp.model.PaymentRequestModel;
import com.bjit.PaymentApp.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    Logger logger = Logger.getLogger("PaymentController");

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
