package rabbitmq_test1;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import bean.Host;
import bean.Producer;

public class Send {

	private final static String QUEUE_NAME = "hello";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		
		Producer producer = new Producer(Host.HOST4);
		producer.publish(QUEUE_NAME, false);
		
		Scanner sc = new Scanner(System.in);
		while (true) {
			String in = sc.nextLine();
			if ("exit".equals(in)) {
				System.out.println("bye bye!");
				producer.send("对方下线了".getBytes("UTF-8"));
				break;
			}
			
			producer.send(in.getBytes("UTF-8"));
		}
		
		producer.close();
	}

}
