package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dto.AttendanceDto;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dto.OrderDto;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotAvailableException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.mapper.IMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "orders")
public class OrderController {

    private final IOrderService orderService;
    private final IMapper mapperDto;

    @Autowired
    public OrderController(IOrderService orderService, IMapper mapperDto) {
        this.orderService = orderService;
        this.mapperDto = mapperDto;
    }

    @GetMapping(value = "/")
    public List<OrderDto> showOrders(@RequestParam(value = "fr", defaultValue = "0") @PositiveOrZero int firstElement,
                                     @RequestParam(value = "mx", defaultValue = "15") @PositiveOrZero int maxResult) {
        return mapperDto.mapAll(orderService.readAll(firstElement, maxResult), OrderDto.class);
    }

    @GetMapping(value = "/sorted")
    public List<OrderDto> showOrders(@RequestParam(value = "ds", defaultValue = "default") String parameter,
                                     @RequestParam(value = "fr", defaultValue = "0") @PositiveOrZero int firstElement,
                                     @RequestParam(value = "mx", defaultValue = "15") @PositiveOrZero int maxResult) {
        return mapperDto.mapAll(orderService.readAllSorted(parameter, firstElement, maxResult), OrderDto.class);
    }

    @GetMapping(value = "/{id}")
    public OrderDto showOrder(@PathVariable(value = "id") @Positive long index) {
        return mapperDto.map(orderService.read(index), OrderDto.class);
    }

    @GetMapping(value = "/{id}/attendances")
    public List<AttendanceDto> showAttendances(@PathVariable(value = "id") @Positive long index) {
        return mapperDto.mapAll(orderService.getAttendances(index), AttendanceDto.class);
    }

    @PutMapping(value = "/{id}/attendances/{id2}")
    public void addAttendance(@PathVariable(value = "id") @Positive long orderIndex,
                              @PathVariable(value = "id2") @Positive long attendanceIndex) {
        orderService.addAttendance(orderIndex, attendanceIndex);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOrder(@PathVariable(value = "id") @Positive long index) {
        orderService.delete(index);
    }

    @PutMapping(value = "/{id}")
    //FIXME : Fix update with LocalDate
    public void updateOrder(@PathVariable(value = "id") @Positive long index,
                            @RequestBody @Validated(OrderDto.class) OrderDto object) {
        if (index != object.getId()) {
            throw new ElementNotAvailableException("Page index: " + index + " not matched object index: " + object.getId());
        }
        orderService.update(mapperDto.map(object, Order.class));
    }

    @PostMapping(value = "/")
    //FIXME : Fix update with LocalDate
    public void createOrder(@RequestBody @Validated(OrderDto.class) OrderDto object) {
        orderService.create(mapperDto.map(object, Order.class));
    }
}
