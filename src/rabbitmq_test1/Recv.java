package rabbitmq_test1;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import bean.Constant;
import bean.Consumer;
import bean.Host;

public class Recv {
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Consumer consumer = new Consumer(Host.WIN, Constant.QUEUE_NAME_TEST, false, new Handler());
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
	    consumer.subscribe();
	}

}
