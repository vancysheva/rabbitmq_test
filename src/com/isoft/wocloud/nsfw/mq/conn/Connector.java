package com.isoft.wocloud.nsfw.mq.conn;

public interface Connector {

//	public Channel getChannel();
//	public Connection getConnection();
	public void close() throws Exception;
}
