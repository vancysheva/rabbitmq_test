package bean;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class Consumer implements com.rabbitmq.client.Consumer {
	private Connector connector;
	
	/**
	 * constructor
	 * @param host
	 * @param queue
	 * @param durable
	 * @throws IOException 
	 */
	public Consumer(Host host) throws IOException {
		this.connector = new Connector(host);
	}
	
	/**
	 * subscribe message from queue
	 * @throws IOException
	 */
	public void subscribe(String queue, boolean durable) throws IOException {
		this.connector.getChannel().queueDeclare(queue, durable, false, false, null);
		this.connector.getChannel().basicConsume(queue, false, this);
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


	
	@Override
	public void handleConsumeOk(String consumerTag) {
		System.out.println("handleConsumeOk = " + consumerTag);
		
	}



	@Override
	public void handleCancelOk(String consumerTag) {
		System.out.println("handleCancelOk = " + consumerTag);
	}



	@Override
	public void handleCancel(String consumerTag) throws IOException {
		System.out.println("handleCancel = " + consumerTag);
	}



	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
		String message = new String(body, "UTF-8");
		System.out.println(" [x] Received '" + message + "' " + consumerTag);
	}



	@Override
	public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
		System.out.println("handleShutdownSignal =" + consumerTag);
	}



	@Override
	public void handleRecoverOk(String consumerTag) {
		System.out.println("handleRecoverOk =" + consumerTag);
	}
}
