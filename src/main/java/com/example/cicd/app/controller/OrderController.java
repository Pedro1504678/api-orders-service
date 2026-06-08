package com.example.cicd.app.controller;

import com.example.cicd.app.dtos.Order;
import io.awspring.cloud.sns.core.SnsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class OrderController {

    private final SnsTemplate snsTemplate;

    @Value("${app.sns.topic-arn}")
    private String topicArn;

    public OrderController(SnsTemplate snsTemplate) {
        this.snsTemplate = snsTemplate;
    }

    @PostMapping
    public ResponseEntity<String> criar(@RequestBody Order order){
        snsTemplate.convertAndSend(topicArn, order);
        return ResponseEntity.accepted().body("Pedido " + order.id() +  " publicado no SNS");
    }
}
