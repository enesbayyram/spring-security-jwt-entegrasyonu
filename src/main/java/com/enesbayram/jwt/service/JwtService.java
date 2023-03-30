package com.enesbayram.jwt.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET_KEY = "EnesBayramEnesBayramEnesBayramEnesBayramEnesBayramEnesBayramXYZ";
	
	private long expiredTime = 5*60*1000; 
	
	public String findUsernameFromToken(String token) {
		return exportToken(token, Claims::getSubject);
	}
	
	public <T> T exportToken(String token , Function<Claims, T> claimsFunction) {
		final Claims claims = Jwts.parserBuilder().
				setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
		return claimsFunction.apply(claims);
	}
	
	public boolean tokenControl(String token , UserDetails userDetails) {
		String username = findUsernameFromToken(token);
		return username.equals(userDetails.getUsername()) &&  new Date().before(exportToken(token, Claims::getExpiration));
	}
	
	public String generateToken(UserDetails user) {
		return Jwts.builder()
				.setClaims(new HashMap<>())
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiredTime))
				.signWith(getKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	public Key getKey()
	{
		byte[] key = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(key);
	}
}
