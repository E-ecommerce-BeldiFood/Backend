package com.example.Orders.component;

import com.example.Orders.exception.OrderNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@NoArgsConstructor
public class RabbitMqExistenceProduct {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqExistenceProduct.class);

    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    @Value("${rabbitmq.existence.queue}")
    private String ExistenceQueue;

    public Object checkProductExistence(Long productId) throws OrderNotFoundException {
        LOGGER.info(String.format("Message sent -> %s", productId));

        Object response= rabbitTemplate.convertSendAndReceive(ExistenceQueue, productId);
        LOGGER.info(String.format("Boolean -> %s", response));
        return Boolean.parseBoolean(response.toString());


    }

}
