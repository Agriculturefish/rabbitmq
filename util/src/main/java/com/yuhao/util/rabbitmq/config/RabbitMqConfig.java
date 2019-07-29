package com.yuhao.util.rabbitmq.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.developer.SerializationFeature;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;


/**
 *
 * @author daiyy
 * RabbitMQ配置读取文件
 */
@Configuration
//@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitMqConfig {
    @Value("${spring.rabbitmq.addresses}")
    private String addresses;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.publisher-confirms}")
    private Boolean publisherConfirms;
    @Value("${spring.rabbitmq.vhost}")
    private String virtualHost;

    // 构建mq实例工厂
    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(addresses);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setPublisherConfirms(publisherConfirms);
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }

    /**
     * 配置MQ传输序列化对象
     *
     * @return Jackson2JsonMessageConverter
     */
    // @Bean
    // public Jackson2JsonMessageConverter jsonMessageConverter() {
    //     ObjectMapper mapper = new ObjectMapper();
    //     mapper.registerModule(new JodaModule());
    //     mapper.enable(MapperFeature.USE_GETTERS_AS_SETTERS);
    //     mapper.enable(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS);
    //     mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    //     mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    //     mapper.enable(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS);
    //     mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    //     mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    //     return new Jackson2JsonMessageConverter(mapper);
    // }

    /**
     * 配置管理器
     */
    @Bean
    public RabbitAdmin rabbitAdmin(){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
        rabbitAdmin.setIgnoreDeclarationExceptions(true);
        return rabbitAdmin;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

    private void declare(String queueName, DirectExchange exchange, String routingKey) {
        RabbitAdmin admin = rabbitAdmin();
        Queue queue = new Queue(queueName, true, false, false);
        admin.declareQueue(queue);
        admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(routingKey));
    }

}
