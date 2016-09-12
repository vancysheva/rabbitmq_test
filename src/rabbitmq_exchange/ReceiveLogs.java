package rabbitmq_exchange;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import bean.Connector;
import bean.Constant;
import bean.Host;

public class ReceiveLogs {

	public static void main(String[] args) throws Exception {
		Connector connector = new Connector(Host.WIN);
		Channel channel = connector.getChannel();
		
		channel.exchangeDeclare(Constant.EXCHANGE_NAME_LOG, "fanout");
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, Constant.EXCHANGE_NAME_LOG, "");
		
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		
		Consumer consumer = new DefaultConsumer(channel) {
		      @Override
		      public void handleDelivery(String consumerTag, Envelope envelope,
		                                 AMQP.BasicProperties properties, byte[] body) throws IOException {
		        String message = new String(body, "UTF-8");
		        System.out.println(" [x] Received '" + message + "'");
		      }
		    };
		    channel.basicConsume(queueName, true, consumer);
	}

}
