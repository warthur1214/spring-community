package com.warthur.wechat.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * Created by warth on 2018/4/10.
 */
public class JwtUtil {

	public static final long EXPIRE_TIME = 2 * 3600 * 1000;
	public static final String JWT_ID = "JwtId";
	private static final String SECRET = "43455454gjixiuowrmkhdiuhs#^&(klefk!";

	public static String createJwtToken(String userInfo) {
		return Jwts.builder()
				.setId(JWT_ID)
				.setSubject(userInfo)
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
				.compact();
	}

	public static String decryJwtToken(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
}
