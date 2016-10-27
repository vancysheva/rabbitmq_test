package com.isoft.wocloud.nsfw.mq.queue;

public enum Queue {
	
	DOWNLOAD_QUEUE("download_queue"), 
	HANDLER_QUEUE("handler_queue"),
	VALIDATE_QUEUE("validate_queue"),
	DB_QUEUE("db_queue");
	
	private String name;
	
	Queue(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.name();
	}
}
