package com.isoft.wocloud.nsfw.mq.client;

import com.isoft.wocloud.nsfw.mq.handle.ConsumeHandler;

public interface ConsumerClient {

	public void accept(ConsumeHandler handle);
}
