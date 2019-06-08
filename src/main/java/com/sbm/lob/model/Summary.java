package com.sbm.lob.model;

import java.math.BigDecimal;

public class Summary {
    private final Order.OrderType type;
    private final BigDecimal price;
    private final double quantity;

    public Summary(Order.OrderType type, BigDecimal price, double quantity) {
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public Order.OrderType getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Summary{" +
                "type=" + type +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
