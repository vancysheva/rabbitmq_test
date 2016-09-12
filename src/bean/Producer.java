package bean;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;

public class Producer {
	private Connector connector;
	private String queue;
	private boolean durable;
	
	/**
	 * constructor
	 * @param host
	 * @param queue
	 * @param durable
	 * @throws IOException 
	 */
	public Producer(Host host, String queue, boolean durable) throws IOException {
		this.connector = new Connector(host);
		this.queue = queue;
		this.durable = durable;
	}
	
	/**
	 * publish
	 * @param queue
	 * @param durable
	 * @throws IOException
	 */
	public void register() throws IOException {
		this.connector.getChannel().queueDeclare(queue, durable, false, false, null);
	}
	
	/**
	 * send message
	 * @param body
	 * @throws IOException
	 */
	public void publish(byte[] body, BasicProperties props) throws IOException {
		this.connector.getChannel().basicPublish("", this.queue, props, body);
	}
	
	/**
	 * colse
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
