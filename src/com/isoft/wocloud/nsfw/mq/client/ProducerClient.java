package com.isoft.wocloud.nsfw.mq.client;

import java.io.IOException;

public interface ProducerClient {
	
	public void send(String routingKey, byte[] body) throws IOException;
}
