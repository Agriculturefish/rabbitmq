package com.yuhao.util.rabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * 用于配置交换机和队列对应关系
 * 新增消息队列应该按照如下步骤
 * 1、增加queue bean，参见queueXXXX方法
 * 2、增加queue和exchange的binding
 * @author daiyy
 * @create 2018-4-12 上午10:33
 **/
@Configuration
@AutoConfigureAfter(RabbitMqConfig.class)
public class RabbitMqExchangeConfig {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqExchangeConfig.class);

    /**
     * 默认交换机
     */
    @Bean
    public DirectExchange defaultExchange() {

        logger.info("绑定direct交换机");
        return new DirectExchange(RabbitmqConstants.EXCHANGE_D_A);
    }
    /**
     * 获取队列A
     * @return
     */
    @Bean
    public Queue queueA() {
        logger.info("创建direct队列");
        return new Queue(RabbitmqConstants.QUEUE_D_A, true);
    }

    @Bean
    public Binding bindingA(){
        logger.info("绑定direct与队列");
        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(RabbitmqConstants.ROUTINGKEY_D_A);
    }

  /*  @Bean
    public Queue queueTA() {
        return new Queue(RabbitmqConstants.QUEUE_T_A);
    }

    @Bean
    public Queue queueTA2() {
        return new Queue(RabbitmqConstants.QUEUE_T_A2);
    }

    @Bean
    public TopicExchange coreExchange() {
        return new TopicExchange(RabbitmqConstants.EXCHANGE_T_A);
    }

    @Bean
    public TopicExchange paymentExchange() {
        return new TopicExchange(RabbitmqConstants.EXCHANGE_T_A2);
    }

    @Bean
    public Binding bindingCoreExchange(Queue queueTA, TopicExchange coreExchange) {
        return BindingBuilder.bind(queueTA).to(coreExchange).with(RabbitmqConstants.ROUTINGKEY_T_A);
    }

    @Bean
    public Binding bindingPaymentExchange(Queue queueTA2, TopicExchange paymentExchange) {
        return BindingBuilder.bind(queueTA2).to(paymentExchange).with(RabbitmqConstants.ROUTINGKEY_T_A2);
    }*/




}
