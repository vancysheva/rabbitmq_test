package com.isoft.wocloud.nsfw.mq.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import com.isoft.wocloud.nsfw.mq.client.MQClient;
import com.isoft.wocloud.nsfw.mq.client.ProducerClient;
import com.isoft.wocloud.nsfw.mq.queue.Queue;
import com.isoft.wocloud.nsfw.mq.route.Route;
import com.isoft.wocloud.nsfw.mq.util.MQUtil;

public class TestSend {

	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		MQClient client = MQUtil.getMQClient();
		ProducerClient producer = client.getProducerClient();
		
		Scanner sc = new Scanner(System.in);
		while (true) {
			String in = sc.nextLine();
			if ("exit".equals(in)) {
				System.out.println("bye bye!");
				producer.send("", "下线了".getBytes("UTF-8"));
				break;
			}
			producer.send(new Route(Queue.DB_QUEUE).toString(), in.getBytes("UTF-8"));
		}
	}

}
