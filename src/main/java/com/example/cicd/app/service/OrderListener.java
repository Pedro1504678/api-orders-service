package com.example.cicd.app.service;

import com.example.cicd.app.dtos.Order;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    private static final Logger log = LoggerFactory.getLogger(OrderListener.class);

    @SqsListener("${app.sqs.queue-name}")
    public void receive(Order order) {
        log.info("Pedido recebido e processado: {}", order);
    }
}
