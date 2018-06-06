package com.warthur.community.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * Created by warth on 2018/4/10.
 */
public class JwtUtil {

	public static final long EXPIRE_TIME = 2 * 3600 * 1000;
	public static final String JWT_AUTH_ID = "JWT_AUTH_ID";
	private static final String SIGN_SECRET = "a036692d9cc18ea29d94f634aaaca197";

	public static String createJwtToken(String userInfo) {
		return Jwts.builder()
				.setId(JWT_AUTH_ID)
				.setSubject(userInfo)
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS512, SIGN_SECRET)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
				.compact();
	}

	public static Claims decryJwtToken(String token) {
		return Jwts.parser()
				.setSigningKey(SIGN_SECRET)
				.parseClaimsJws(token)
				.getBody();
	}
}
