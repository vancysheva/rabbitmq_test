package com.isoft.wocloud.nsfw.mq.handle;

import com.rabbitmq.client.Consumer;

public interface ConsumeHandler extends Consumer {

	public void handle(String msg);
}
