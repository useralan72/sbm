package com.sbm.lob.service;

import com.sbm.lob.model.Order;
import com.sbm.lob.model.Summary;

import java.util.Comparator;
import java.util.List;

public interface LiveOrderBoardService {

    Order registerOrder(Order order);

    Boolean cancelOrder(Order order);

    List<Order> findAllOrders();

    List<Summary> boardSummary(Order.OrderType orderType, Comparator<Summary> comparator);

    List<Summary> boardSummary();
}
