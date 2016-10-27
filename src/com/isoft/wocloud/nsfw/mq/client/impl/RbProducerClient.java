package com.isoft.wocloud.nsfw.mq.client.impl;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.isoft.wocloud.nsfw.mq.client.ProducerClient;
import com.isoft.wocloud.nsfw.mq.conn.impl.RbConnector;
import com.isoft.wocloud.nsfw.mq.exchange.ExchangeType;
import com.isoft.wocloud.nsfw.mq.host.Host;
import com.rabbitmq.client.MessageProperties;

public class RbProducerClient implements ProducerClient {

	private RbConnector connector;
	private ExchangeType exchange;
	
	public RbProducerClient(Host host, ExchangeType exchange) {
		this.connector = new RbConnector(host);
		this.exchange = exchange;
		try {
			this.connector.getChannel().exchangeDeclare(exchange.getName(), exchange.getType());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void send(String routingKey, byte[] msg) throws IOException {
		//MessageProperties.PERSISTENT_TEXT_PLAIN
		this.connector.getChannel().basicPublish(exchange.getName(), routingKey, null, msg);
	}
	
	public void close() throws IOException, TimeoutException {
		this.connector.close();
	}
}
