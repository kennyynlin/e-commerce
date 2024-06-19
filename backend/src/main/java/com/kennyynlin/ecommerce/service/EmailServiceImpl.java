package com.kennyynlin.ecommerce.service;

import com.kennyynlin.ecommerce.dto.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public void sendEmail(Purchase purchase) {
        //TODO: real implementation of sending email
        try {
            Thread.sleep(10000);
        }catch (InterruptedException e) {
            logger.error(e.getMessage());
        }

        logger.info("Sending email to {}", purchase.getCustomer().getEmail());
        logger.info(purchase.getOrderItems().toString());
    }
}