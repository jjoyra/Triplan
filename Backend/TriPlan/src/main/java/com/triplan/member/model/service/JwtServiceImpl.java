package com.triplan.member.model.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.triplan.expcetion.UnAuthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtServiceImpl implements JwtService {
	
	private static final String SALT = "ssafytriplanhh";
	
	private static final int ACCESS_TOKEN_EXPIRE = 1;
	private static final int REFRESH_TOKEN_EXPIRE = 2;

	@Override
	public <T> String createAccessToken(String key, T data) {
		return create(key, data, "access-token", 1000 * 60 * 60 * ACCESS_TOKEN_EXPIRE);
	}

	@Override
	public <T> String createRefreshToken(String key, T data) {
		return create(key, data, "refresh-token", 1000 * 60 * 60 * 24 * 7 * REFRESH_TOKEN_EXPIRE);
	}

	@Override
	public <T> String create(String key, T data, String subject, long expir) {
		Claims claims = Jwts.claims()
				.setSubject(subject)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expir));
		
		claims.put(key, data);
		
		String jwt = Jwts.builder()
				.setHeaderParam("type", "JWT")
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS256, this.generateKey())
				.compact();
		
		return jwt;
	}

	private byte[] generateKey() {
		byte[] key = null;
		
		try {
			key = SALT.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return key;
	}

	@Override
	public Map<String, Object> get(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String jwt = request.getHeader("access-token");
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(jwt);
		} catch (Exception e) {
			throw new UnAuthorizedException();
		}
		Map<String, Object> value = claims.getBody();
		System.out.println("value: {} " + value);
		
		return value;
	}

	@Override
	public String getMemberId() {
		return (String) this.get("member").get("memberId");
	}

	@Override
	public boolean checkToken(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
			System.out.println("claims: {}" + claims);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
