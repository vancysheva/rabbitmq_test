package bean;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Connector {
	private Connection conn;
	private Channel channel;
	private Host host;
	
	/**
	 * constructor
	 * @param host
	 * @param queue
	 * @param durable
	 */
	public Connector(Host host) {
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
	
	/**
	 * close the channel and connecton
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public void close() throws IOException, TimeoutException {
		this.channel.close();
		this.conn.close();
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Host getHost() {
		return host;
	}

	public void setHost(Host host) {
		this.host = host;
	}
}
