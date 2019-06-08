package com.sbm.lob.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Order {
    public enum OrderType { SELL, BUY }

    private final UUID orderId;
    private final long userId;
    private final OrderType type;
    private final BigDecimal pricePerKilo;
    private final double quantity;

    public Order(long userId, OrderType type, BigDecimal pricePerKilo, double quantity) {
        this.orderId = UUID.randomUUID();
        this.userId = userId;
        this.type = type;
        this.pricePerKilo = pricePerKilo;
        this.quantity = quantity;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public long getUserId() {
        return userId;
    }

    public OrderType getType() {
        return type;
    }

    public BigDecimal getPricePerKilo() {
        return pricePerKilo;
    }

    public double getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", type=" + type +
                ", pricePerKilo=" + pricePerKilo +
                ", quantity=" + quantity +
                '}';
    }
}
