package com.warthur.community.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Created by warth on 2018/4/10.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

	public static final String STRING_INIT = "";
	public static final String JWT_TOKEN_HEADER = "Authorization";
	public static final String USER_NAMESPACE = "wechat_authorize_user:";

	public static final long TIMESTAMP_DEVIATION = 10000;
	public static final long AUTH_TOKEN_EXPIRE = 7210 * 1000;
	public static final String API_REQUEST_LIMITS = "api_reqeust_limit:";
}
