package com.sbm.lob.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.junit.Assert.assertNotNull;

public class OrderTest {

    Order order;

    @Before
    public void setUp() {

    }

    @Test
    public void givenConstructorShouldCreateUUID() {
        order = new Order(100L, Order.OrderType.BUY, new BigDecimal(1.5, MathContext.DECIMAL64), 2.3);

        assertNotNull(order.getType());
        assertNotNull(order.getOrderId());
    }
}
