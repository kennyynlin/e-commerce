package com.kennyynlin.ecommerce.dto;

import com.kennyynlin.ecommerce.entity.Address;
import com.kennyynlin.ecommerce.entity.Customer;
import com.kennyynlin.ecommerce.entity.Order;
import com.kennyynlin.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
