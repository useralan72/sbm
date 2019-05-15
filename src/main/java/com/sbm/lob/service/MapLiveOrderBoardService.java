package com.sbm.lob.service;

import com.sbm.lob.model.Order;
import com.sbm.lob.model.Summary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MapLiveOrderBoardService implements LiveOrderBoardService{
    private static final Logger LOGGER = LoggerFactory.getLogger(MapLiveOrderBoardService.class);

    private final Map<UUID, Order> orderMap;

    public MapLiveOrderBoardService() {
        orderMap = new ConcurrentHashMap<>();
    }

    @Override
    public Order registerOrder(Order order) {
        LOGGER.info("Registering order {}", order.toString());
        return orderMap.putIfAbsent(order.getOrderId(), order);
    }

    @Override
    public Boolean cancelOrder(Order order) {
        LOGGER.info("Cancelling order {}", order.toString());
        Order removedOrder = orderMap.remove(order.getOrderId());
        if (removedOrder == null) {
            throw new NoSuchElementException("No Order with id " + order.getOrderId());
        }
        return true;
    }

    @Override
    public List<Order> findAllOrders() {
        return orderMap
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Summary> boardSummary(Order.OrderType orderType, Comparator<Summary> comparator) {
        return findAllOrders()
                .stream()
                .filter(p -> p.getType().equals(orderType))
                .collect(Collectors.groupingBy(Order::getPricePerKilo))
                .entrySet()
                .stream()
                .map(k -> new Summary(orderType, k.getKey(), k.getValue().stream().collect(Collectors.summingDouble(Order::getQuantity))))
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public List<Summary> boardSummary() {
        Comparator<Summary> priceComparator = Comparator.comparing(Summary::getPrice);
        List<Summary> allTypesSummary = new ArrayList<>();
        allTypesSummary.addAll(boardSummary(Order.OrderType.SELL, priceComparator));
        allTypesSummary.addAll(boardSummary(Order.OrderType.BUY, priceComparator.reversed()));
        return allTypesSummary;
    }

}
