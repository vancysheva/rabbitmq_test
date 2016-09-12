package rabbitmq_exchange_direct;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;

import bean.Connector;
import bean.Constant;
import bean.Host;

public class EmitLogDirect {

	public static void main(String[] args) throws Exception {
		Connector connector = new Connector(Host.WIN);
		Channel channel = connector.getChannel();
		
		channel.exchangeDeclare(Constant.EXCHANGE_NAME_DIRECT, "direct");
		
		Scanner sc = new Scanner(System.in);
		while (true) {
			String in = sc.nextLine();
			String routingKey = in.substring(0, in.indexOf(":"));
			if ("exit".equals(in)) {
				System.out.println("bye bye!");
				channel.basicPublish(Constant.EXCHANGE_NAME_DIRECT, routingKey, null, "下线了".getBytes("UTF-8"));
				break;
			}
			
			channel.basicPublish(Constant.EXCHANGE_NAME_DIRECT, routingKey, null, in.getBytes("UTF-8"));
		}
		
		connector.close();
	}
}
