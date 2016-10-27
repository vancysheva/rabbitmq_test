package com.isoft.wocloud.nsfw.mq.conn.impl;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.isoft.wocloud.nsfw.mq.conn.Connector;
import com.isoft.wocloud.nsfw.mq.host.Host;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RbConnector implements Connector {
	
	private Connection conn;
	private Channel channel;
	private Host host;
	
	/**
	 * constructor
	 * @param host
	 * @param queue
	 * @param durable
	 */
	public RbConnector(Host host) {
		this.host = host;
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host.getIp());
		factory.setVirtualHost(host.getVhost());
		factory.setUsername(host.getUsername());
		factory.setPassword(host.getPassword());
		
		try {
			conn = factory.newConnection();
			channel = conn.createChannel();
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void close() throws IOException, TimeoutException {
		this.channel.close();
		this.conn.close();
	}

	public Channel getChannel() {
		return this.channel;
	}

	public Host getHost() {
		return host;
	}
}
