package ma.beldifood.productcatalogservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

<<<<<<< HEAD
//@Configuration
//public class RabbitMqConfig {
//    @Value("${rabbitmq.exchange.name}")
//    private String exchange;
//
//    @Value("${rabbitmq.routing.key}")
//    private String routingKey;
//
//    @Value("${rabbitmq.existence.queue}")
//    private String ExistenceQueue;
//
//
//
//    @Bean
//    public Queue ExistenceQueue() {
//        return new Queue(ExistenceQueue);
//    }
//
//
//    @Bean
//    public DirectExchange exchange() {
//        return new DirectExchange(exchange);
//    }
//
//
//    @Bean
//    public Binding bindingReviewProduct() {
//        return BindingBuilder.bind(ExistenceQueue())
//                .to(exchange())
//                .with(routingKey);
//    }
//
//
//    @Bean
//    public MessageConverter messageConverter(ObjectMapper objectMapper) {
//        return new Jackson2JsonMessageConverter(objectMapper);
//    }
//
//    @Bean
//    public RabbitTemplate newRabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(messageConverter);
//        rabbitTemplate.setReplyTimeout(10000);
//        rabbitTemplate.setReceiveTimeout(10000);
//        return rabbitTemplate;
//    }
//
//
//}
=======
@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.existence.queue}")
    private String ExistenceQueue;
    @Value("${review.json.queue}")
    private String jsonQueue;
    @Value("${json.routing.key}")
    private String routingJsonKey;


    @Bean
    public Queue ExistenceQueue() {
        return new Queue(ExistenceQueue);
    }


    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange);
    }


    @Bean
    public Binding bindingReviewProduct() {
        return BindingBuilder.bind(ExistenceQueue())
                .to(exchange())
                .with(routingKey);
    }


    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate newRabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setReplyTimeout(10000);
        rabbitTemplate.setReceiveTimeout(10000);
        return rabbitTemplate;
    }


}
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
