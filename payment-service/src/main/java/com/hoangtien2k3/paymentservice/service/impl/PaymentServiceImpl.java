package com.hoangtien2k3.paymentservice.service.impl;

import com.hoangtien2k3.paymentservice.constant.AppConstant;
import com.hoangtien2k3.paymentservice.dto.OrderDto;
import com.hoangtien2k3.paymentservice.dto.PaymentDto;
import com.hoangtien2k3.paymentservice.exception.wrapper.PaymentNotFoundException;
import com.hoangtien2k3.paymentservice.helper.PaymentMappingHelper;
import com.hoangtien2k3.paymentservice.repository.PaymentRepository;
import com.hoangtien2k3.paymentservice.service.PaymentService;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Transactional
@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;

    @Override
    public List<PaymentDto> findAll() {
        log.info("*** PaymentDto List, service; fetch all payments *");
        return this.paymentRepository.findAll()
                .stream()
                .map(PaymentMappingHelper::map)
                .map(p -> {
                    p.setOrderDto(this.restTemplate.getForObject(AppConstant.DiscoveredDomainsApi
                            .ORDER_SERVICE_API_URL + "/" + p.getOrderDto().getOrderId(), OrderDto.class));
                    return p;
                })
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public PaymentDto findById(final Integer paymentId) {
        log.info("PaymentDto, service; fetch payment by id");
        return this.paymentRepository.findById(paymentId)
                .map(PaymentMappingHelper::map)
                .map(p -> {
                    p.setOrderDto(this.restTemplate.getForObject(AppConstant.DiscoveredDomainsApi
                            .ORDER_SERVICE_API_URL + "/" + p.getOrderDto().getOrderId(), OrderDto.class));
                    return p;
                })
                .orElseThrow(() -> new PaymentNotFoundException(String.format("Payment with id: %d not found", paymentId)));
    }

    @Override
    public PaymentDto save(final PaymentDto paymentDto) {
        log.info("PaymentDto, service; save payment");
        return PaymentMappingHelper.map(this.paymentRepository
                .save(PaymentMappingHelper.map(paymentDto)));
    }

    @Override
    public PaymentDto update(final PaymentDto paymentDto) {
        log.info("PaymentDto, service; update payment");
        return PaymentMappingHelper.map(this.paymentRepository
                .save(PaymentMappingHelper.map(paymentDto)));
    }

    @Override
    public void deleteById(final Integer paymentId) {
        log.info("Void, service; delete payment by id");
        this.paymentRepository.deleteById(paymentId);
    }

}
