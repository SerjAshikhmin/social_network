package tests;

import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.model.enums.OrderStatus;
import com.senla.courses.autoservice.service.interfaces.IOrderService;
import config.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class OrderServiceTest {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private TestData testData;

    @BeforeAll
    public static void startOrderServiceTests() {
        log.info("Starting order service tests");
    }

    @Test
    public void validateNewOrderAdding() {
        log.info("Validating new order adding");
        assertEquals(orderService.addOrder(1, LocalDateTime.of(2020, Month.JUNE, 1, 11, 0),
                LocalDateTime.of(2020, Month.JUNE, 1, 12, 0),
                LocalDateTime.of(2020, Month.JUNE, 1, 13, 0),
                "Oil change", 1000, 1, 1, "Evgeniy", OrderStatus.ACCEPTED), 1);
        assertEquals(orderService.addOrder(1, LocalDateTime.of(2020, Month.JUNE, 1, 11, 0),
                LocalDateTime.of(2020, Month.JUNE, 1, 12, 0),
                LocalDateTime.of(2020, Month.JUNE, 1, 13, 0),
                "Oil change", 1000, 1, 1, "UnknownMaster", OrderStatus.ACCEPTED), 0);
    }

    @Test
    public void validateOrderRemoving() {
        log.info("Validating order removing");
        assertEquals(orderService.removeOrder(1), 1);
        assertEquals(orderService.removeOrder(5), 0);
    }

    @Test
    public void validateGettingAllOrders() {
        log.info("Validating getting all orders");
        assertEquals(orderService.getAllOrders(), testData.getOrdersList());
    }

    @Test
    public void validateGettingAllOrdersSorted() {
        log.info("Validating getting all orders sorted");
        List<Order> allOrdersSorted = new ArrayList<>(testData.getOrdersList());
        Collections.sort(allOrdersSorted, (order, t1) -> {
            if(order.getCost() > t1.getCost())
                return 1;
            else if(order.getCost()< t1.getCost())
                return -1;
            else
                return 0;
        });
        assertEquals(orderService.getAllOrdersSorted("byCost"), allOrdersSorted);

        allOrdersSorted = new ArrayList<>(testData.getOrdersList());
        Collections.sort(allOrdersSorted, Comparator.comparing(Order::getEndDate));
        assertEquals(orderService.getAllOrdersSorted("byEndDate"), allOrdersSorted);

        allOrdersSorted = new ArrayList<>(testData.getOrdersList());
        Collections.sort(allOrdersSorted, Comparator.comparing(Order::getStartDate));
        assertEquals(orderService.getAllOrdersSorted("byStartDate"), allOrdersSorted);

        allOrdersSorted = new ArrayList<>(testData.getOrdersList());
        Collections.sort(allOrdersSorted, Comparator.comparing(Order::getSubmissionDate));
        assertEquals(orderService.getAllOrdersSorted("bySubmissionDate"), allOrdersSorted);

        assertEquals(orderService.getAllOrdersSorted("wrongSortMethod"), testData.getOrdersList());
    }

    @Test
    public void validateGettingAllOrdersInProgress() {
        log.info("Validating getting all orders in progress");
        assertEquals(orderService.getAllOrdersInProgress(null), testData.getAllOrdersInProgress());
    }

    @Test
    public void validateGettingNearestFreeDate() {
        log.info("Validating getting nearest free date");
        assertEquals(orderService.getNearestFreeDate(), testData.getNearestFreeDate());
    }

    @Test
    public void validateGettingMastersByOrder() {
        log.info("Validating getting masters by order");
        assertEquals(orderService.getMastersByOrder(1), testData.getOrdersList().get(0).getMasters());
    }

    @Test
    public void validateGettingOrdersByPeriod() {
        log.info("Validating getting orders by period");
        assertEquals(orderService.getOrdersByPeriod(LocalDateTime.of(2020, Month.JUNE, 1, 9, 0),
                                                    LocalDateTime.of(2020, Month.JUNE, 30, 18, 0),
                                             null).get(0), testData.getOrdersList().get(0));
    }

    @Test
    public void validateFindingOrderById() {
        log.info("Validating finding order by id");
        assertEquals(orderService.findOrderById(1), testData.getOrdersList().get(0));
        assertEquals(orderService.findOrderById(10), null);
    }

    @Test
    public void validateCancellingOrder() {
        log.info("Validating order cancelling");
        assertEquals(orderService.cancelOrder(2), 1);
        assertEquals(orderService.cancelOrder(5), 0);
    }

    @Test
    public void validateClosingOrder() {
        log.info("Validating order closing");
        assertEquals(orderService.closeOrder(3), 1);
        assertEquals(orderService.closeOrder(5), 0);
    }

    @Test
    public void validateOrderTimeUpdating() {
        log.info("Validating order time updating");
        Order order = testData.getOrdersList().get(0);
        assertEquals(orderService.updateOrderTime(order, LocalDateTime.of(2020, Month.JUNE, 1, 12, 0),
                LocalDateTime.of(2020, Month.JUNE, 1, 15, 0)), 1);
    }

    @AfterAll
    public static void endMasterServiceTests() {
        log.info("Order service tests are completed");
    }

}
