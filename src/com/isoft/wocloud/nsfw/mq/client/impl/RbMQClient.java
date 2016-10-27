package com.isoft.wocloud.nsfw.mq.client.impl;

import com.isoft.wocloud.nsfw.mq.client.ConsumerClient;
import com.isoft.wocloud.nsfw.mq.client.MQClient;
import com.isoft.wocloud.nsfw.mq.client.ProducerClient;
import com.isoft.wocloud.nsfw.mq.exchange.ExchangeType;
import com.isoft.wocloud.nsfw.mq.host.Host;
import com.isoft.wocloud.nsfw.mq.queue.Queue;
import com.rabbitmq.client.AMQP.Exchange;

public class RbMQClient implements MQClient {
	
	private static ProducerClient producerClient;
	private static ConsumerClient consumerClient;
	private static ExchangeType exchange = ExchangeType.DIRECT;
	
	
	@Override
	public ProducerClient getProducerClient() {
		if (producerClient == null) {
			producerClient = new RbProducerClient(Host.HOST4, exchange);
		}
		return producerClient;
	}

	@Override
	public ConsumerClient getConsumerClient(Queue queue) {
		if (consumerClient == null) {
			consumerClient = new RbConsumerClient(Host.HOST4, true, exchange, queue);
		}
		return consumerClient;
	}

}
