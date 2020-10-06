package com.senla.courses.autoservice.controller;

import com.senla.courses.autoservice.dto.MasterDto;
import com.senla.courses.autoservice.dto.OrderDto;
import com.senla.courses.autoservice.dto.mappers.MasterMapper;
import com.senla.courses.autoservice.dto.mappers.OrderMapper;
import com.senla.courses.autoservice.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;
    @Autowired
    MasterMapper masterMapper;
    @Autowired
    OrderMapper orderMapper;

    @GetMapping("")
    public ResponseEntity<List<OrderDto>> onGetAllOrders() {
        final List<OrderDto> orders = new ArrayList<>();
        orderService.getAllOrders().forEach(order -> {
            orders.add(orderMapper.orderToOrderDto(order));
        });
        return orders != null &&  !orders.isEmpty()
                ? new ResponseEntity<>(orders, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> onFindOrderById(@PathVariable("id") int id) {
        final OrderDto order = orderMapper.orderToOrderDto(orderService.findOrderById(id));
        return order != null
                ? new ResponseEntity<>(order, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE,
                             produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> onAddOrder(@RequestBody OrderDto order) {
        return orderService.addOrder(orderMapper.orderDtoToOrder(order)) == 1
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> onRemoveOrder(@PathVariable("id") int id) {
        return orderService.removeOrder(id) == 1
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> onCancelOrder(@PathVariable("id") int id) {
        return orderService.cancelOrder(id) == 1
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/close/{id}")
    public ResponseEntity<?> onCloseOrder(@PathVariable("id") int id) {
        return orderService.closeOrder(id) == 1
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/shiftEndTimeOrders")
    public ResponseEntity<?> onShiftEndTimeOrders(@RequestParam("hours") int hours, @RequestParam("minutes") int minutes) {
        orderService.shiftEndTimeOrders(hours, minutes);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sorted/{sortBy}")
    public ResponseEntity<List<OrderDto>> onGetAllOrdersSorted(@PathVariable("sortBy") String sortBy) {
        final List<OrderDto> orders = new ArrayList<>();
        orderService.getAllOrdersSorted(sortBy).forEach(order -> {
            orders.add(orderMapper.orderToOrderDto(order));
        });
        return orders != null && !orders.isEmpty()
                ? new ResponseEntity<>(orders, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/inProgress/{sortBy}")
    public ResponseEntity<List<OrderDto>> onGetAllOrdersInProgress(@PathVariable("sortBy") String sortBy) {
        final List<OrderDto> orders = new ArrayList<>();
        orderService.getAllOrdersInProgress(sortBy).forEach(order -> {
            orders.add(orderMapper.orderToOrderDto(order));
        });
        return orders != null && !orders.isEmpty()
                ? new ResponseEntity<>(orders, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/masters/{id}")
    public ResponseEntity<List<MasterDto>> onGetMastersByOrder(@PathVariable("id") int id) {
        final List<MasterDto> masters = new ArrayList<>();
        orderService.getMastersByOrder(id).forEach(master -> {
            masters.add(masterMapper.masterToMasterDto(master));
        });
        return masters != null && !masters.isEmpty()
                ? new ResponseEntity<>(masters, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/ordersByPeriod")
    public ResponseEntity<List<OrderDto>> onGetOrdersByPeriod (@RequestParam("startPeriod") String startPeriodStr,
                                                             @RequestParam("endPeriod") String endPeriodStr, @RequestParam("sortBy") String sortBy) {
        final List<OrderDto> orders = new ArrayList<>();
        LocalDateTime startPeriod = LocalDateTime.parse(startPeriodStr);
        LocalDateTime endPeriod = LocalDateTime.parse(endPeriodStr);
        orderService.getOrdersByPeriod(startPeriod, endPeriod, sortBy).forEach(order -> {
            orders.add(orderMapper.orderToOrderDto(order));
        });
        return orders != null && !orders.isEmpty()
                ? new ResponseEntity<>(orders, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/nearestDate")
    public ResponseEntity<LocalDateTime> onGetNearestFreeDate() {
        LocalDateTime nearestFreeDate = orderService.getNearestFreeDate();
        return nearestFreeDate != null
                ? new ResponseEntity<>(nearestFreeDate, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public int importOrder(String fileName) {
        return orderService.importOrder(fileName);
    }

    public boolean exportOrder(int id, String fileName) {
        return orderService.exportOrder(id, fileName);
    }

    public void saveState() {
        orderService.saveState();
    }

    public void loadState() {
        orderService.loadState();
    }
}
