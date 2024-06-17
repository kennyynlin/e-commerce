package com.kennyynlin.ecommerce.service;

import com.kennyynlin.ecommerce.dto.Purchase;
import com.kennyynlin.ecommerce.dto.PurchaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class CheckoutServiceImpl implements CheckoutService{
    private final EmailService emailService;
    private final OrderService orderService;

    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    private static final Logger logger = LoggerFactory.getLogger(CheckoutServiceImpl.class);

    @Autowired
    public CheckoutServiceImpl(EmailService emailService, OrderService orderService) {
        this.emailService = emailService;
        this.orderService = orderService;
    }

    @Override
    public PurchaseResponse process(Purchase purchase) {
        try {
            executor.submit(() -> {
                try {
                    emailService.sendEmail(purchase);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            Future<PurchaseResponse> future = executor.submit(() -> orderService.place(purchase));
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error processing purchase: {}", e.getMessage(), e);
            return null;
        } finally {
            executor.shutdown();
        }
    }
}
