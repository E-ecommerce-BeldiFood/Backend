package ma.beldifood.productcatalogservice.component;

import lombok.AllArgsConstructor;
import ma.beldifood.productcatalogservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
//
//@Component
//@AllArgsConstructor
//public class ProductExistenceListener {
//    @Autowired
//    private final ProductRepository productRepository;
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ProductExistenceListener.class);
//
//    @RabbitListener(queues = "${rabbitmq.existence.queue}")
//    @SendTo("${rabbitmq.existence.queue}")
//    public Boolean checkProductExistence(Long productId) {
//        LOGGER.info("Checking Product existence");
//        return productRepository.existsById(productId);
//    }
//
//}