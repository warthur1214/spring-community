package com.warthur.common;

/**
 * Created by warth on 2018/4/10.
 */
public final class Constants {

	private static final String MINIAPP_NAMESPACE = "mini_app:";
	public static final String JWT_TOKEN_HEADER = "Authorization";
	public static String SESSION_KEY;
	public static String ACCESS_TOKEN;

	static {
		SESSION_KEY = MINIAPP_NAMESPACE + "session_key";
		ACCESS_TOKEN = MINIAPP_NAMESPACE + "access_token";
	}
}
