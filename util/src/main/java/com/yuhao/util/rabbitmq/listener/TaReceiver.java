package com.yuhao.util.rabbitmq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TaReceiver {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

   /* @RabbitHandler
    @RabbitListener(queues = "ta")
    public void user(String msg) {
        logger.info("ta receive message: "+msg);
    }*/
}
