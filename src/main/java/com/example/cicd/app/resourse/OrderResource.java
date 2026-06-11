package com.example.cicd.app.resourse;

import com.example.cicd.app.dtos.OrderRequestDto;
import io.awspring.cloud.sns.core.SnsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderResource {

    private final SnsTemplate snsTemplate;

    @Value("${app.sns.topic-arn}")
    private String topicArn;

    public OrderResource(SnsTemplate snsTemplate) {
        this.snsTemplate = snsTemplate;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody OrderRequestDto order){
        snsTemplate.convertAndSend(topicArn, order);
        return ResponseEntity.accepted().body("Order " + order.id() +  " published in the SNS");
    }
}
