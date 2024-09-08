package com.example.multiple_db.order.Controller;

import com.example.multiple_db.order.entity.Order;
import com.example.multiple_db.order.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.RespectBinding;

@RestController
@RequestMapping("order/api")
public class OrderController {

    @Autowired
    private OrderRepo orderRepo;

    @PostMapping("/save")
    public ResponseEntity<Order> saveorder(@RequestBody Order order)
    {
        Order savedOrder = orderRepo.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
}
