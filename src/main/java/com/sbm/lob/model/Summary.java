package com.sbm.lob.model;

public class Summary {
    private final Order.OrderType type;
    private final double price;
    private final double quantity;

    public Summary(Order.OrderType type, double price, double quantity) {
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public Order.OrderType getType() {
        return type;
    }

    public double getPrice() {
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
