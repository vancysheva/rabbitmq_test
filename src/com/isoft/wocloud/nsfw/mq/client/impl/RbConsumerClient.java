package com.isoft.wocloud.nsfw.mq.client.impl;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.isoft.wocloud.nsfw.mq.client.ConsumerClient;
import com.isoft.wocloud.nsfw.mq.conn.impl.RbConnector;
import com.isoft.wocloud.nsfw.mq.exchange.ExchangeType;
import com.isoft.wocloud.nsfw.mq.handle.ConsumeHandler;
import com.isoft.wocloud.nsfw.mq.host.Host;
import com.isoft.wocloud.nsfw.mq.queue.Queue;
import com.isoft.wocloud.nsfw.mq.route.Route;
import com.rabbitmq.client.Channel;

public class RbConsumerClient implements ConsumerClient {

	private RbConnector connector;
	private ConsumeHandler handler;
	private boolean durable;
	private Queue queue;
	private ExchangeType exchange;
	
	private boolean isSubscribed = false;
	
	public RbConsumerClient(Host host, boolean durable, ExchangeType exchange, Queue queue) {
		this.connector = new RbConnector(host);
		this.durable = durable;
		this.queue = queue;
		this.exchange = exchange;
	}
	
	@Override
	public void accept(ConsumeHandler handler) {
		this.handler = handler;
		if (!isSubscribed) {
			try {
				this.subscribe();
				isSubscribed = true;
			} catch (IOException e) {
				isSubscribed =false;
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * subscribe message from queue
	 * @throws IOException
	 */
	private void subscribe() throws IOException {
		Channel channel = this.connector.getChannel();
		channel.exchangeDeclare(exchange.getName(), exchange.getType());
		channel.queueDeclare(queue.getName(), durable, false, false, null);
		channel.queueBind(queue.getName(), exchange.getName(), new Route(queue).toString());
		int prefetchCount = 1;
		channel.basicQos(prefetchCount);
		// 第二个参数默认是true， 设置true为消息处理完毕自动返回RabbitMQ，消息从队列里移除
		channel.basicConsume(queue.getName(), true, this.handler);
	}
	
	public void close() throws IOException, TimeoutException {
		this.connector.close();
	}
}
