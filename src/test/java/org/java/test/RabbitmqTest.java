package org.java.test;


import org.java.rabbitmq.RabbitmqProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: zgd
 * @Date: 18/08/30 15:28
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqTest {

    @Autowired
    private RabbitmqProducer producer;


    @Test
    public void sendMsg() throws Exception {
        producer.sendMessage("点对点队列");
        producer.sendMessageA("AAAAAAAAA");
        producer.sendMessageB("BBBBBBBBB");
        producer.sendMessageLike("********");
    }
}