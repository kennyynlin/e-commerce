package com.kennyynlin.ecommerce.service;

import com.kennyynlin.ecommerce.dto.Purchase;

public interface EmailService {
    void sendEmail(Purchase purchase) throws InterruptedException;
}
