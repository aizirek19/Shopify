package com.ecommerce.Shopify.controllers;

import com.ecommerce.Shopify.dto.PaymentDTO;
//import com.ecommerce.Shopify.services.PaymentService;
import com.ecommerce.Shopify.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long paymentId) {
        PaymentDTO paymentDTO = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(paymentDTO);
    }

    @PostMapping
    public ResponseEntity<Void> createPayment(@RequestBody PaymentDTO paymentDTO) {
        paymentService.createPayment(paymentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<Void> updatePayment(@PathVariable Long paymentId, @RequestBody PaymentDTO paymentDTO) {
        paymentService.updatePayment(paymentId, paymentDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long paymentId) {
        paymentService.deletePayment(paymentId);
        return ResponseEntity.noContent().build();
    }
}
