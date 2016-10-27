package com.isoft.wocloud.nsfw.mq.handle.impl;

import java.io.IOException;

import com.isoft.wocloud.nsfw.mq.handle.ConsumeHandler;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public abstract class RbConsumeHandler implements ConsumeHandler {
	
	public abstract void handle(String msg);
	
	@Override
	public void handleDelivery(String consumerTag,
            Envelope envelope,
            AMQP.BasicProperties properties,
            byte[] body) throws IOException {
		this.handle(new String(body));
	}
	
	@Override
	public void handleConsumeOk(String consumerTag) {
		
	}

	@Override
	public void handleCancelOk(String consumerTag) {
		
	}

	@Override
	public void handleCancel(String consumerTag) throws IOException {
		
	}

	@Override
	public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
		
	}

	@Override
	public void handleRecoverOk(String consumerTag) {
		
	}

}
