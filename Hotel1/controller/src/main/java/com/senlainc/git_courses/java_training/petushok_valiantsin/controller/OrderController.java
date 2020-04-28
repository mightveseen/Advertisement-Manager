package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dto.OrderDto;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.mapper.IMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
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
    public List<OrderDto> showOrders() {
        return mapperDto.mapAll(orderService.readAll(0, 5), OrderDto.class);
    }

    @GetMapping(value = "/{id}")
    public String showGuest(@PathVariable(value = "id") @Positive long index) {
        return orderService.read(index).toString();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteGuest(@PathVariable(value = "id") @Positive long index) {
        orderService.delete(index);
    }
}
