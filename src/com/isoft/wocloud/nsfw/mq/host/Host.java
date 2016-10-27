package com.isoft.wocloud.nsfw.mq.host;

public enum Host {

	HOST4("10.111.0.4", 5672, "test", "root", "admin");
	
	private String ip;
	private int port;
	private String vhost;
	private String username;
	private String password;
	
	Host(String ip, int port, String vhost, String username, String password) {
		this.ip = ip;
		this.port = port;
		this.vhost = vhost;
		this.username = username;
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public String getVhost() {
		return vhost;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
