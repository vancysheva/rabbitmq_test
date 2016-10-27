package com.isoft.wocloud.nsfw.mq.exchange;

public enum ExchangeType {
	FANOUT("fanout"), 
	DIRECT("direct"), 
	TOPIC("topic"), 
	HEADERS("headers");
	
	/**
	 * 交换机类型
	 */
	private String type;
	
	/**
	 * 默认的交换机名字
	 */
	private String name = "nsfw-exchange";
	
	ExchangeType(String type) {
		this.type = type;
	}
	
	ExchangeType(ExchangeType type, String name) {
		this.type = type.getName();
		this.name = name;
	}
	
	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.type;
	}
}
