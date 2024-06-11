package com.kennyynlin.ecommerce.service;

import com.kennyynlin.ecommerce.dao.CustomerRepository;
import com.kennyynlin.ecommerce.dto.Purchase;
import com.kennyynlin.ecommerce.dto.PurchaseResponse;
import com.kennyynlin.ecommerce.entity.Address;
import com.kennyynlin.ecommerce.entity.Customer;
import com.kennyynlin.ecommerce.entity.Order;
import com.kennyynlin.ecommerce.entity.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutServiceImplMockTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CheckoutServiceImpl checkoutService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void placeOrderTest() {
        Purchase purchase = createPurchase();
        doReturn(purchase.getCustomer()).when(customerRepository).save(any(Customer.class));
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

        assertNotNull(purchaseResponse.getOrderTrackingNUmber());
        Mockito.verify(customerRepository, times(1)).save(any(Customer.class));
    }

    private Purchase createPurchase() {
        Purchase purchase = new Purchase();

        Customer customer = createCustomer();
        Address shippingAddress = createAddress();
        Address billingAddress = createAddress();
        Order order = createOrder();
        Set<OrderItem> orderItems = createOrderItems();

        purchase.setCustomer(customer);
        purchase.setShippingAddress(shippingAddress);
        purchase.setBillingAddress(billingAddress);
        purchase.setOrder(order);
        purchase.setOrderItems(orderItems);

        return purchase;
    }

    private Customer createCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john@doe.com");
        return customer;
    }

    private Address createAddress() {
        Address address = new Address();
        address.setStreet("Street");
        address.setCity("City");
        address.setState("State");
        address.setCountry("Country");
        address.setZipCode("00000");
        return address;
    }

    private Order createOrder() {
        Order order = new Order();
        order.setTotalPrice(BigDecimal.valueOf(36.98));
        order.setTotalQuantity(2);
        return order;
    }

    private Set<OrderItem> createOrderItems() {
        Set<OrderItem> orderItems = new HashSet<>();

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setImageUrl("assets/images/products/coffeemugs/coffeemug-luv2code-1000.png");
        orderItem1.setQuantity(1);
        orderItem1.setUnitPrice(BigDecimal.valueOf(18.99));
        orderItem1.setProductId(26L);

        OrderItem orderItem2 = new OrderItem();
        orderItem1.setImageUrl("assets/images/products/mousepads/mousepad-luv2code-1000.png");
        orderItem1.setQuantity(1);
        orderItem1.setUnitPrice(BigDecimal.valueOf(17.99));
        orderItem1.setProductId(51L);
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        return orderItems;
    }



}
