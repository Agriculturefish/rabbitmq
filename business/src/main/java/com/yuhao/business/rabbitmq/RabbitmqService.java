
package com.yuhao.business.rabbitmq;

import com.yuhao.util.rabbitmq.config.RabbitmqConstants;
import com.yuhao.util.rabbitmq.user.RabbitMqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RabbitmqService {

	@Autowired
	RabbitMqSender rabbitMqSender;
	
	public void sendMsg(String msg) {

		try{
			rabbitMqSender.sendDirectMsg(RabbitmqConstants.EXCHANGE_D_A,RabbitmqConstants.ROUTINGKEY_D_A,msg);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

      
