package com.kennyynlin.ecommerce.service;

import com.kennyynlin.ecommerce.dto.Purchase;
import com.kennyynlin.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
