package org.java.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: zgd
 * @Date: 18/08/30 15:21
 * @Description: 生产者
 */
@Component
public class RabbitmqProducer {

    @Autowired
    private AmqpTemplate rabbitTemplate;
    public void sendMessage(String msg) {
        String context = "[生产者A发送消息]\t"+new Date().toLocaleString() + "\t------------> "+msg  ;
        System.out.println(context);
        this.rabbitTemplate.convertAndSend("message.direct.myexchange","message.myqueue", msg);
    }

    public void sendMessageA(String msg) {
        String context = "[生产者A发送消息]\t"+new Date().toLocaleString() + "\t------------> "+msg  ;
        System.out.println(context);
        this.rabbitTemplate.convertAndSend("exchange","topic.queue_a", msg);
    }

    public void sendMessageB(String msg) {
        String context = "[生产者B发送消息]\t"+new Date().toLocaleString() + "\t------------> "+msg  ;
        System.out.println(context);
        this.rabbitTemplate.convertAndSend("exchange","topic.queue_b", msg);
    }

    public void sendMessageLike(String msg) {
        String context = "[生产者like发送消息]\t"+new Date().toLocaleString() + "\t------------> "+msg  ;
        System.out.println(context);
        this.rabbitTemplate.convertAndSend("exchange","topic.aa",msg);
    }

}
