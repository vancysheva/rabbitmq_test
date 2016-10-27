package com.isoft.wocloud.nsfw.mq.test;

import com.isoft.wocloud.nsfw.mq.client.ConsumerClient;
import com.isoft.wocloud.nsfw.mq.client.MQClient;
import com.isoft.wocloud.nsfw.mq.handle.impl.RbConsumeHandler;
import com.isoft.wocloud.nsfw.mq.queue.Queue;
import com.isoft.wocloud.nsfw.mq.util.MQUtil;

public class TestRecv {

	public static void main(String[] args) {
		MQClient client = MQUtil.getMQClient();
		ConsumerClient consumer = client.getConsumerClient(Queue.VALIDATE_QUEUE);
		System.out.println("TestRecv[" + Queue.VALIDATE_QUEUE.getName() + "] running...");
		consumer.accept(new RbConsumeHandler() {
			@Override
			public void handle(String msg) {
				System.out.println("Received " + msg);
			}
		});
	}

}
