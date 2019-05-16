package com.sbm.lob.service;

import com.sbm.lob.model.Order;
import com.sbm.lob.model.Summary;

import java.util.Comparator;
import java.util.List;

public interface LiveOrderBoardService {

    /**
     * Register an Order on the Live Order Board
     * @param order
     * @return
     */
    Order registerOrder(Order order);

    /**
     * Cancel an Order
     * @param order
     * @return
     */
    Boolean cancelOrder(Order order);

    /**
     * Find all orders on the board
     * @return
     */
    List<Order> findAllOrders();

    List<Summary> boardSummary(Order.OrderType orderType, Comparator<Summary> comparator);

    List<Summary> boardSummary();
}
