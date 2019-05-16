package com.sbm.lob.service;

import com.sbm.lob.model.Order;
import com.sbm.lob.model.Summary;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class MapLiveOrderBoardServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapLiveOrderBoardServiceTest.class);

    LiveOrderBoardService liveOrderBoardService;
    Order order;

    @Before
    public void setUp() {
        liveOrderBoardService = new MapLiveOrderBoardService();
    }

    @Test
    public void givenOrderShouldRegister() {
        Order order = createOrder();

        Order registeredOrder = liveOrderBoardService.registerOrder(order);

        assertNull(registeredOrder);

    }

    @Test
    public void givenOrderToCancelShouldCancel() {
        Order order = createOrder();
        liveOrderBoardService.registerOrder(order);
        List<Order> orders = liveOrderBoardService.findAllOrders();
        assertEquals(1, orders.size());

        Boolean response = liveOrderBoardService.cancelOrder(order);
        assertTrue(response);
        orders = liveOrderBoardService.findAllOrders();
        assertEquals(0, orders.size());
    }

    @Test
    public void givenLiveOrdersSummaryForSELLIsAsExpected() {
        registerSellOrders();
        List<Order> orders = liveOrderBoardService.findAllOrders();
        assertEquals(4, orders.size());

        Comparator<Summary> comparator = Comparator.comparing(Summary::getPrice);
        List<Summary> summaryList = liveOrderBoardService.boardSummary(Order.OrderType.SELL, comparator);
        assertNotNull(summaryList);
        summaryList.stream().forEach(s -> LOGGER.info(s.toString()));
        assertEquals(306.0, summaryList.stream().findFirst().get().getPrice(), 0);
        assertEquals(5.5, summaryList.stream().findFirst().get().getQuantity(), 0);
    }

    @Test
    public void givenLiveOrdersSummaryForBUYIsAsExpected() {
        registerBuyOrders();
        List<Order> orders = liveOrderBoardService.findAllOrders();
        assertEquals(8, orders.size());

        Comparator<Summary> comparator = Comparator.comparing(Summary::getPrice).reversed();
        List<Summary> summaryList = liveOrderBoardService.boardSummary(Order.OrderType.BUY, comparator);
        assertNotNull(summaryList);
        summaryList.stream().forEach(s -> LOGGER.info(s.toString()));
        assertEquals(310.0, summaryList.stream().findFirst().get().getPrice(), 0);
        assertEquals(10.2, summaryList.stream().findFirst().get().getQuantity(), 0);
    }

    @Test
    public void givenLiveOrdersSummaryIsAsExpected() {
        registerSellOrders();
        registerBuyOrders();
        List<Order> orders = liveOrderBoardService.findAllOrders();
        assertEquals(12, orders.size());

        List<Summary> summaryList = liveOrderBoardService.boardSummary();
        assertNotNull(summaryList);
        summaryList.stream().forEach(s -> LOGGER.info(s.toString()));
        assertEquals(306.0, summaryList.stream().findFirst().get().getPrice(), 0);
        assertEquals(5.5, summaryList.stream().findFirst().get().getQuantity(), 0);
    }

    private Order createOrder() {
        long USER_ID = 1l;
        double QUANTITY = 20.0;
        double PRICE_PER_KILO = 300.0;
        return new Order(USER_ID, Order.OrderType.SELL, PRICE_PER_KILO, QUANTITY);
    }

    private void registerSellOrders() {
        Order order1 = new Order(1l, Order.OrderType.SELL, 306.0, 3.5);
        Order order2 = new Order(23l, Order.OrderType.SELL, 310.0, 1.2);
        Order order3 = new Order(99l, Order.OrderType.SELL, 307.0, 1.5);
        Order order4 = new Order(10000l, Order.OrderType.SELL, 306.0, 2.0);
        liveOrderBoardService.registerOrder(order1);
        liveOrderBoardService.registerOrder(order2);
        liveOrderBoardService.registerOrder(order3);
        liveOrderBoardService.registerOrder(order4);
    }

    private void registerBuyOrders() {
        Order order1 = new Order(1l, Order.OrderType.BUY, 306.0, 3.5);
        Order order2 = new Order(23l, Order.OrderType.BUY, 310.0, 1.2);
        Order order3 = new Order(99l, Order.OrderType.BUY, 307.0, 1.5);
        Order order4 = new Order(10000l, Order.OrderType.BUY, 306.0, 2.0);
        Order order5 = new Order(10000l, Order.OrderType.BUY, 306.0, 2.0);
        Order order6 = new Order(10000l, Order.OrderType.BUY, 310.0, 6.0);
        Order order7 = new Order(10000l, Order.OrderType.BUY, 306.0, 2.0);
        Order order8 = new Order(10000l, Order.OrderType.BUY, 310.0, 3.0);
        liveOrderBoardService.registerOrder(order1);
        liveOrderBoardService.registerOrder(order2);
        liveOrderBoardService.registerOrder(order3);
        liveOrderBoardService.registerOrder(order4);
        liveOrderBoardService.registerOrder(order5);
        liveOrderBoardService.registerOrder(order6);
        liveOrderBoardService.registerOrder(order7);
        liveOrderBoardService.registerOrder(order8);
    }
}
