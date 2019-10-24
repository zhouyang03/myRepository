package org.java.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zgd
 * @Date: 18/08/30 15:18
 * @Description: 队列配置
 */
@Configuration
public class RabbitmqConfig {


    // 消息交换
    String MESSAGE_EXCHANGE = "message.direct.myexchange";
    // 消息队列名称
    String MESSAGE_QUEUE_NAME = "message.myqueue";
    // 消息路由键
    String MESSAGE_ROUTE_KEY = "message.myroute";
    /**
     * 消息队列声明
     *
     * @return
     */
    @Bean
    public Queue messageQueue() {
        return QueueBuilder.durable(MESSAGE_QUEUE_NAME)
                .build();
    }

    /**
     * 交换配置
     *
     * @return
     */
    @Bean
    public DirectExchange messageDirectExchange() {
        return (DirectExchange) ExchangeBuilder.directExchange(MESSAGE_EXCHANGE)
                .durable(true)
                .build();
    }


    /**
     * 消息绑定
     *
     * @return
     */
    @Bean
    public Binding messageBinding() {
        return BindingBuilder.bind(messageQueue())
                .to(messageDirectExchange())
                .with(MESSAGE_ROUTE_KEY);
    }




    final static String QUEUE_A = "topic.queue_a";
    final static String QUEUE_B = "topic.queue_b";
    //模糊队列,满足topic.*的都会发送到这个队列
    final static String QUEUE_LIKE = "topic.queue_like";
    //------------队列queue只和消费者直接对接-----------
    @Bean
    public Queue Queue_A() {
        return new Queue(QUEUE_A);
    }
    @Bean
    public Queue Queue_B() {
        return new Queue(QUEUE_B);
    }
    @Bean
    public Queue Queue_Like() {
        return new Queue(QUEUE_LIKE);
    }
    //--------------生产者通过exchange交换器来决定发送到哪个queue-------------
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    //-------------绑定exchange和queue,通过with()中的值来作为绑定key,让exchange知道生产者的消息该分配到哪条queue-----------------
    /**
     * 使用"topic.queue_a"来作为key来匹配生产者和队列,如果生产者使用这个key,就会经过exchange发到QUEUE_A队列
     * @param Queue_A
     * @param exchange
     * @return
     */
    @Bean
    //入参中的Queue_A和exchange是方法名,不是参数名
    Binding bindingExchangeA(Queue Queue_A, TopicExchange exchange) {
        return BindingBuilder.bind(Queue_A).to(exchange).with("topic.queue_a");
    }

    /**
     * 使用"topic.queue_b"来作为key来匹配生产者和队列,如果生产者使用这个key,就会经过exchange发到QUEUE_B队列
     * @param Queue_B
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeB(Queue Queue_B, TopicExchange exchange) {
        return BindingBuilder.bind(Queue_B).to(exchange).with("topic.queue_b");
    }

    /**
     * 使用"topic.#"来作为key来模糊匹配,生产者使用的key是topic.开头的,都会经过exchange发到QUEUE_LIKE队列
     * @param Queue_Like
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeLike(Queue Queue_Like, TopicExchange exchange) {
        return BindingBuilder.bind(Queue_Like).to(exchange).with("topic.*");
    }

/*rabbitmq的延迟队列*/




    /*----------------分割线--------------*/

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }


}