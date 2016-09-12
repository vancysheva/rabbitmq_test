package rabbitmq_test2;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import bean.Connector;
import bean.Constant;
import bean.Host;

public class Worker {

	public static void main(String[] args) throws Exception {
		Connector connector = new Connector(Host.WIN);
		
		connector.getChannel().queueDeclare(Constant.QUEUE_NAME_NEW_TASK, true, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		
		connector.getChannel().basicQos(1);
		
		final Consumer consumer = new DefaultConsumer(connector.getChannel()) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");

		        System.out.println(" [x] Received '" + message + "'");
		        try {
		            doWork(message);
		          } finally {
		            System.out.println(" [x] Done");
		            connector.getChannel().basicAck(envelope.getDeliveryTag(), false);
		          }
			}
		};
		
		connector.getChannel().basicConsume(Constant.QUEUE_NAME_NEW_TASK, false, consumer);
	}

	private static void doWork(String task) {
	    for (char ch : task.toCharArray()) {
	      if (ch == '.') {
	        try {
	          Thread.sleep(1000);
	        } catch (InterruptedException _ignored) {
	          Thread.currentThread().interrupt();
	        }
	      }
	    }
	  }
}
