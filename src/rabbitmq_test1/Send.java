package rabbitmq_test1;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.client.ShutdownSignalException;

import bean.Constant;
import bean.Host;
import bean.Producer;

public class Send {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, TimeoutException {
		
		Producer producer = new Producer(Host.WIN, Constant.QUEUE_NAME_TEST, false);
		producer.register();

		Scanner sc = new Scanner(System.in);
		while (true) {
			String in = sc.nextLine();
			if ("exit".equals(in)) {
				System.out.println("bye bye!");
				producer.publish("下线了".getBytes("UTF-8"), null);
				break;
			}
			
			producer.publish(in.getBytes("UTF-8"), null);
		}
		
		producer.close();
	}

}
