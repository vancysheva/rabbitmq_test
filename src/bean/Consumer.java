package bean;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class Consumer {
	private Connector connector;
	private String queue;
	private boolean durable;
	private com.rabbitmq.client.Consumer handler;
	
	/**
	 * constructor
	 * @param host
	 * @param queue
	 * @param durable
	 * @throws IOException 
	 */
	public Consumer(Host host, String queue, boolean durable, com.rabbitmq.client.Consumer handler) throws IOException {
		this.connector = new Connector(host);
		this.queue = queue;
		this.durable = durable;
		this.handler = handler;
	}
	
	/**
	 * subscribe message from queue
	 * @throws IOException
	 */
	public void subscribe() throws IOException {
		this.connector.getChannel().queueDeclare(this.queue, this.durable, false, false, null);
		this.connector.getChannel().basicConsume(this.queue, false, handler);
	}
	
	/**
	 * close
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public void close() throws IOException, TimeoutException {
		this.connector.close();
	}

	public Connector getConnector() {
		return connector;
	}

	public void setConnector(Connector connector) {
		this.connector = connector;
	}

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public boolean isDurable() {
		return durable;
	}

	public void setDurable(boolean durable) {
		this.durable = durable;
	}
}
