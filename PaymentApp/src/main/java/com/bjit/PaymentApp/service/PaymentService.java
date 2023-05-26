package com.bjit.PaymentApp.service;

import com.bjit.PaymentApp.entity.PaymentEntity;
import com.bjit.PaymentApp.model.PaymentRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {

    ResponseEntity<Object> getPayment(Long id);
    ResponseEntity<Iterable<PaymentEntity>> getPaymentByPaymentType(String type);
    ResponseEntity<Iterable<PaymentEntity>> getAllPayments();
    ResponseEntity<Object> createPayment(PaymentRequestModel requestModel);
    ResponseEntity<Object> updatePayment(Long id, PaymentRequestModel requestModel);
    ResponseEntity<Object> deletePayment(Long id);

}
