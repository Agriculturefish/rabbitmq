package com.yuhao.util.rabbitmq.user;

import java.util.UUID;

import com.yuhao.util.rabbitmq.config.RabbitmqConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * rabbitmq发送消息工具类
 *
 * @author dennis
 * @create 2018-4-12 上午10:33
 **/
@Component
public class RabbitMqSender implements RabbitTemplate.ConfirmCallback{

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqSender.class);


    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 回调
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info(" 回调id:" + correlationData);
        if (ack) {
            logger.info("消息成功消费");
        } else {
            logger.info("消息消费失败:" + cause);
        }
    }

    /**
     * 发送到 指定routekey的指定queue
     * @param routeKey
     * @param obj
     */
    public void sendDirectMsg(String exchange,String routeKey,Object obj) throws Exception {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("producer direct send: " + obj);
        this.rabbitTemplate.convertAndSend(exchange, routeKey , obj, correlationData);
    }

    /**
     * 所有发送到Topic Exchange的消息被转发到所有关心RouteKey中指定Topic的Queue上
     * @param routeKey
     * @param obj
     */
    public void sendRabbitmqTopic(String exchange,String routeKey,Object obj) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("producer topic send ta: " + obj);
        this.rabbitTemplate.convertAndSend(exchange, routeKey , obj, correlationData);
    }

    /**
     * 所有发送到Topic Exchange的消息被转发到所有关心RouteKey中指定Topic的Queue上
     * @param routeKey
     * @param obj
     */
    public void sendRabbitmqTopic2(String routeKey,Object obj) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("producer topic send ta2: " + correlationData.getId());
        this.rabbitTemplate.convertAndSend(RabbitmqConstants.EXCHANGE_T_A2, routeKey , obj, correlationData);
    }

    /**
     * ----------------------------------start - SHF-----------------------------------
     * 发送到   用户添加的队列中
     * 指定交换机 和  路由规则
     * @param obj
     */
    public void sendUserAddTopic(Object obj)throws Exception {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("producer send: " + correlationData.getId());
        this.rabbitTemplate.convertAndSend("", "", obj, correlationData);
    }


    /**
     * 发送到 积分变动的 queue
     * 指定交换机 和  路由规则
     * @param obj
     */
    public void sendIntragatlAddTopic(Object obj)throws Exception {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("producer send: " + correlationData.getId());
        this.rabbitTemplate.convertAndSend("",  "" , obj, correlationData);
    }
    //----------------------------------end-----------------------------------

    /**
     * 从指定队列中接受消息
     * @author SHF
     * @version 创建时间：2018年7月27日  下午2:17:33
     *  @param queueName
     *  @return
     */
    public Object reciveRabbitMqObject(String queueName) {
        Object object = this.rabbitTemplate.receiveAndConvert(queueName);
        return object;
    }
}
