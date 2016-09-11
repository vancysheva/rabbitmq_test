package rabbitmq_test1;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import bean.Consumer;
import bean.Host;

public class Recv {

	private final static String QUEUE_NAME = "hello";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Consumer consumer = new Consumer(Host.HOST4);
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
	    consumer.subscribe(QUEUE_NAME, false);
	}

}
