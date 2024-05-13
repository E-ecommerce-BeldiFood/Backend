package reviewservice.reviewservice.component;



//
//@Component
//@AllArgsConstructor
//@NoArgsConstructor
//public class RabbitMqExistenceProduct {
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqExistenceProduct.class);
//
//    @Value("${rabbitmq.routing.key}")
//    private String routingKey;
//    @Value("${rabbitmq.existence.queue}")
//    private String ExistenceQueue;
//
//    public Object checkProductExistence(Long productId) throws ReviewNotFoundException {
//        LOGGER.info(String.format("Message sent -> %s", productId));
//
//        Object response= rabbitTemplate.convertSendAndReceive(ExistenceQueue, productId);
//        LOGGER.info(String.format("Boolean -> %s", response));
//        return Boolean.parseBoolean(response.toString());
//
//
//    }
//
//}
