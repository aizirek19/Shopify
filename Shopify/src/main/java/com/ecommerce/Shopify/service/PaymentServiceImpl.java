package com.ecommerce.Shopify.service;

import com.ecommerce.Shopify.dto.PaymentDTO;
import com.ecommerce.Shopify.entities.Payment;
import com.ecommerce.Shopify.mappers.PaymentMapper;
import com.ecommerce.Shopify.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(PaymentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDTO getPaymentById(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found with id: " + paymentId));
        return PaymentMapper.toDTO(payment);
    }

    @Override
    public void createPayment(PaymentDTO paymentDTO) {
        Payment payment = PaymentMapper.toEntity(paymentDTO);
        paymentRepository.save(payment);
    }

    @Override
    public void updatePayment(Long paymentId, PaymentDTO paymentDTO) {
        Payment existingPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found with id: " + paymentId));
        Payment updatedPayment = PaymentMapper.toEntity(paymentDTO);
        updatedPayment.setId(existingPayment.getId());
        paymentRepository.save(updatedPayment);
    }

    @Override
    public void deletePayment(Long paymentId) {
        paymentRepository.deleteById(paymentId);
    }
}
