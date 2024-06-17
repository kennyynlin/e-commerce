package com.kennyynlin.ecommerce.service;

import com.kennyynlin.ecommerce.dto.Purchase;
import com.kennyynlin.ecommerce.dto.PurchaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class CheckoutServiceImpl implements CheckoutService{
    private final EmailService emailService;
    private final OrderService orderService;
    private final ExecutorService executorService;

    private static final Logger logger = LoggerFactory.getLogger(CheckoutServiceImpl.class);

    @Autowired
    public CheckoutServiceImpl(EmailService emailService, OrderService orderService, ExecutorService executorService) {
        this.emailService = emailService;
        this.orderService = orderService;
        this.executorService = executorService;
    }

    @Override
    public PurchaseResponse process(Purchase purchase) {
        try {
            executorService.submit(() -> {
                try {
                    emailService.sendEmail(purchase);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            Future<PurchaseResponse> future = executorService.submit(() -> orderService.place(purchase));
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error processing purchase: {}", e.getMessage(), e);
            return null;
        }
    }
}
