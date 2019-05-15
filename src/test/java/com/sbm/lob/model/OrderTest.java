package com.sbm.lob.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class OrderTest {

    Order order;

    @Before
    public void setUp() {

    }

    @Test
    public void givenConstructorShouldCreateUUID() {
        order = new Order(100L, Order.OrderType.BUY, 1.5, 2.3);

        assertNotNull(order.getType());
        assertNotNull(order.getOrderId());
    }
}
