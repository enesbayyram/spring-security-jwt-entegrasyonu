package com.enesbayram.jwt.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.enesbayram.jwt.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtService jwtService;
	
//	private HttpSession httpSession;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
//		httpSession = request.getSession(true);
		
		String header = request.getHeader("Authorization");
		String token;
		String username;
		
		if(header==null) {
			filterChain.doFilter(request, response);
			return;
		}
		token = header.substring(7);
		username = jwtService.findUsernameFromToken(token);
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			if(userDetails!=null) {
				if(jwtService.tokenControl(token, userDetails)) {
					UsernamePasswordAuthenticationToken auth = 
							new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
					auth.setDetails(userDetails);
					SecurityContextHolder.getContext().setAuthentication(auth);
//					httpSession.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext().getAuthentication());
				}
			}
		}
		filterChain.doFilter(request, response);
	}

}
