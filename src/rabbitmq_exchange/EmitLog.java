package rabbitmq_exchange;

import java.io.IOException;
import java.util.Scanner;

import com.rabbitmq.client.Channel;

import bean.Connector;
import bean.Constant;
import bean.Host;

public class EmitLog {

	public static void main(String[] args) throws Exception {
		Connector connector = new Connector(Host.WIN);
		Channel channel = connector.getChannel();
		
		channel.exchangeDeclare(Constant.EXCHANGE_NAME_LOG, "fanout");
		
		Scanner sc = new Scanner(System.in);
		while (true) {
			String in = sc.nextLine();
			if ("exit".equals(in)) {
				System.out.println("bye bye!");
				channel.basicPublish(Constant.EXCHANGE_NAME_LOG, "", null, "下线了".getBytes("UTF-8"));
				break;
			}
			
			channel.basicPublish(Constant.EXCHANGE_NAME_LOG, "", null, in.getBytes("UTF-8"));
		}
		
		connector.close();
	}

}
