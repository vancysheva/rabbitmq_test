package rabbitmq_test2;

import com.rabbitmq.client.MessageProperties;

import bean.Constant;
import bean.Host;
import bean.Producer;

public class NewTask {

	public static void main(String[] args) throws Exception {
		Producer producer = new Producer(Host.WIN, Constant.QUEUE_NAME_NEW_TASK, true);
		producer.register();
		
		for (String string : args) {
			producer.publish(string.getBytes("UTF-8"), MessageProperties.PERSISTENT_TEXT_PLAIN);
			System.out.println("[x] Send '" + string +"'");
		}
		
		producer.close();
	}
	
	public static String getMessage(String[] strings) {
		if (strings.length < 1)
			return "Hello World";
		return joinString(strings, " ");
	}
	
	public static String joinString(String[] strings, String delimiter) {
		int length = strings.length;
		if (length == 0) return "";
		StringBuilder builder = new StringBuilder(strings[0]);
		for (int i = 1; i < strings.length; i++) {
			String string = strings[i];
			builder.append(delimiter).append(string);
		}
		
		return builder.toString();
	}
	
}
