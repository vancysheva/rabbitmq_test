package bean;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
	private Connector connector;
	
	/**
	 * constructor
	 * @param host
	 * @param queue
	 * @param durable
	 * @throws IOException 
	 */
	public Producer(Host host) throws IOException {
		this.connector = new Connector(host);
	}
	
	/**
	 * publish
	 * @param queue
	 * @param durable
	 * @throws IOException
	 */
	public void publish(String queue, boolean durable) throws IOException {
		this.connector.getChannel().queueDeclare(queue, durable, false, false, null);
	}
	
	/**
	 * send message
	 * @param body
	 * @throws IOException
	 */
	public void send(byte[] body) throws IOException {
		this.connector.getChannel().basicPublish("", "", null, body);
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
}
