package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping(path = "orders")
public class OrderController {

    private final IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/{id}")
    public String showGuest(@PathVariable(value = "id") @Positive long index) {
        return orderService.getOrder(index).toString();
    }

    @DeleteMapping(value = "/{id}/delete")
    public void deleteGuest(@PathVariable(value = "id") @Positive long index) {
        orderService.delete(index);
    }
}
