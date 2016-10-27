package com.isoft.wocloud.nsfw.mq.util;

import com.isoft.wocloud.nsfw.mq.client.MQClient;
import com.isoft.wocloud.nsfw.mq.client.impl.RbMQClient;

public class MQUtil {

	public static MQClient getMQClient() {
		return new RbMQClient();
	}
}
