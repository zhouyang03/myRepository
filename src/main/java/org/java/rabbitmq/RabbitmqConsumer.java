package org.java.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: zgd
 * @Date: 18/08/30 15:22
 * @Description: 消费者
 */

@Component
public class RabbitmqConsumer {


    @RabbitHandler
    @RabbitListener(queues = "topic.queue_a")
    public void processA(String msg) {
        System.out.println("[消费者queue_a收到消息]:\t" + new Date().toLocaleString()+"\t------------> "+ msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "topic.queue_b")
    public void processB(String msg) {
        System.out.println("[消费者queue_b收到消息]:\t" + new Date().toLocaleString()+"\t------------> "+ msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "topic.queue_like")
    public void processLike(String msg) {
        System.out.println("[模糊匹配的消费者queue_like收到消息]:\t" + new Date().toLocaleString()+"\t------------> "+ msg);
    }


}