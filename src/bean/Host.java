package bean;

public enum Host {

	WIN("10.111.0.4", 5672, "test", "root", "admin"),
	MAC("", 5672, "test", "root", "admin");
	
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

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getVhost() {
		return vhost;
	}

	public void setVhost(String vhost) {
		this.vhost = vhost;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
