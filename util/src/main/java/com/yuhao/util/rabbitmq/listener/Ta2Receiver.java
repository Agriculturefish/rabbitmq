package com.yuhao.util.rabbitmq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Ta2Receiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

   /* @RabbitHandler
    @RabbitListener(queues = "ta2")
    public void user(String msg) {
        logger.info("ta2 receive message: "+msg);
    }*/
}
