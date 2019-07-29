
package com.yuhao.util.rabbitmq.user;

import com.yuhao.util.rabbitmq.config.RabbitmqConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 一个生产者，一个消费者
 * */
@Component
@RabbitListener(queues = "QUEUE_D_A",containerFactory="rabbitListenerContainerFactory")
public class Receiver {
 
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
 
    @RabbitHandler
    public void process(String content) {
        logger.info("A接收处理队列A当中的消息： " + content);
    }
 
}

