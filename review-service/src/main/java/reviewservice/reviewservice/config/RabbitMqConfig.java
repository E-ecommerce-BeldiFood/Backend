package reviewservice.reviewservice.config;

//
//@Configuration
//public class RabbitMqConfig {
//    @Value("${rabbitmq.exchange.name}")
//    private String exchange;
//
//    @Value("${rabbitmq.routing.key}")
//    private String routingKey;
//    @Value("${rabbitmq.existence.queue}")
//    private String ExistenceQueue;
//    //Json Message
//    @Value("${review.json.queue}")
//    private String jsonQueue;
//    @Value("${json.routing.key}")
//    private String routingJsonKey;
//
//    @Bean
//    public Queue JsonQueue(){return new Queue(jsonQueue);}
//    @Bean
//    public Binding bindingGetReview() {
//        return BindingBuilder.bind(JsonQueue())
//                .to(exchange())
//                .with(routingJsonKey);
//    }
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
