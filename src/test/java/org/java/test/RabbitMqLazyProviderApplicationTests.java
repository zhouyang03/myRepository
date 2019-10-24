package org.java.test;




import org.java.config.QueueEnum;
import org.java.rabbitmq.MessageProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
//        (classes = RedisdemoApplication.class)
public class RabbitMqLazyProviderApplicationTests {
    /**
     * 消息队列提供者
     */
    @Autowired
    private MessageProvider messageProvider;

    /**
     * 测试延迟消息消费
     */
    @Test
    public void testLazy() {
        // 测试延迟10秒
        messageProvider.sendMessage("测试延迟消费,写入时间：" + new Date(),
                QueueEnum.MESSAGE_TTL_QUEUE.getExchange(),
                QueueEnum.MESSAGE_TTL_QUEUE.getRouteKey(),
                20000);
    }
}