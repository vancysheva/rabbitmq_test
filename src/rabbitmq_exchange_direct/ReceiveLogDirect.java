package rabbitmq_exchange_direct;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import bean.Connector;
import bean.Constant;
import bean.Host;

public class ReceiveLogDirect {

	public static void main(String[] args) throws Exception {
		Connector connector = new Connector(Host.WIN);
		Channel channel = connector.getChannel();
		
		channel.exchangeDeclare(Constant.EXCHANGE_NAME_DIRECT, "direct");
		String queueName = channel.queueDeclare().getQueue();
		
		if (args.length < 1){
			System.err.println("Usage: ReceiveLogsDirect [info] [warning] [error]");
			System.exit(1);
	    }
		
		for(String severity : args){
			channel.queueBind(queueName, Constant.EXCHANGE_NAME_DIRECT, severity);
	    }
		
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

	    Consumer consumer = new DefaultConsumer(channel) {
	      @Override
	      public void handleDelivery(String consumerTag, Envelope envelope,
	                                 AMQP.BasicProperties properties, byte[] body) throws IOException {
	        String message = new String(body, "UTF-8");
	        System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
	      }
	    };
	    channel.basicConsume(queueName, true, consumer);
	}
}