package com.enesbayram.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.enesbayram.jwt.api.model.LoginRequest;
import com.enesbayram.jwt.api.model.UserDto;
import com.enesbayram.jwt.api.model.UserResponse;
import com.enesbayram.jwt.enums.Role;
import com.enesbayram.jwt.model.User;
import com.enesbayram.jwt.repository.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public UserResponse save(UserDto userDto) {
		User user = User.builder().name(userDto.getName()).surname(userDto.getSurname()).username(userDto.getUsername())
				.password(new BCryptPasswordEncoder().encode(userDto.getPassword())).role(Role.USER).build();

		userRepository.save(user);

//		String token = jwtService.generateToken(user);
		return UserResponse.builder().token("").build();
	}

	public UserResponse authenticate(LoginRequest loginRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		User user = userRepository.findByUsername(loginRequest.getUsername()).get();
		
		String token = jwtService.generateToken(user);
//		SecurityContextHolder.getContext().set
		return UserResponse.builder().token(token).build();
	}
}
