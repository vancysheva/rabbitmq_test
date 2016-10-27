package com.isoft.wocloud.nsfw.mq.client;

import com.isoft.wocloud.nsfw.mq.queue.Queue;

public interface MQClient {
	
	public ProducerClient getProducerClient();
	public ConsumerClient getConsumerClient(Queue queue);
}
