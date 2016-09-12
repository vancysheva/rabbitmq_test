package rabbitmq_test1;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

class Handler implements com.rabbitmq.client.Consumer {
	
	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
		String message = new String(body, "UTF-8");
		System.out.println(" [x] Received '" + message + "'");
	}
	
	@Override
	public void handleConsumeOk(String consumerTag) {
		
		
	}



	@Override
	public void handleCancelOk(String consumerTag) {
		
	}



	@Override
	public void handleCancel(String consumerTag) throws IOException {
		
	}


	
	@Override
	public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {

	}



	@Override
	public void handleRecoverOk(String consumerTag) {
		
	}
}