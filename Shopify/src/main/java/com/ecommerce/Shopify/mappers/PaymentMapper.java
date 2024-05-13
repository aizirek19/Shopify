package com.ecommerce.Shopify.mappers;

import com.ecommerce.Shopify.dto.PaymentDTO;
import com.ecommerce.Shopify.entities.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    static Payment toEntity(PaymentDTO paymentDTO) {
        return null;
    }

    static PaymentDTO toDTO(Payment payment) {
        return null;
    }

    PaymentDTO paymentToPaymentDTO(Payment payment);

    <Payment> Payment paymentDTOToPayment(PaymentDTO paymentDTO);
}
