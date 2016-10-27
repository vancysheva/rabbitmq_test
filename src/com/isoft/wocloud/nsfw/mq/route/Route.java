package com.isoft.wocloud.nsfw.mq.route;

import com.isoft.wocloud.nsfw.mq.queue.Queue;

public class Route {

	/*DOWNLOAD_KEY(Queue.DOWNLOAD_QUEUE),
	THUMBNAIL_KEY(Queue.HANDLER_QUEUE), 
	TUPU_KEY(Queue.VALIDATE_QUEUE), 
	DB_KEY(Queue.DB_QUEUE);*/
	
	private String key;
	
	public Route(Queue queue) {
		switch (queue) {
		case DOWNLOAD_QUEUE:
			this.key = "download";
			break;
		case HANDLER_QUEUE:
			this.key = "thumbnail";
			break;
		case VALIDATE_QUEUE:
			this.key = "tupu";
			break;
		case DB_QUEUE:
			this.key = "db";
			break;
		default:
			break;
		}
	}
	
	public String getKey() {
		return key;
	}
	
	@Override
	public String toString() {
		return this.key;
	}
}
