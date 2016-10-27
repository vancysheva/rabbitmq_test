package com.isoft.wocloud.nsfw.mq.exception;

public class RoutingKeyNotMatchException extends Exception {

	private static final long serialVersionUID = 1L;

	public RoutingKeyNotMatchException(String msg) {
		super(msg);
	}
}
