package com.enesbayram.jwt.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private String name;
	private String surname;
	private String username;
	private String password;
}
