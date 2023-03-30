package com.enesbayram.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/panel")
public class DashboardController {

	@GetMapping("/mesaj")
	public ResponseEntity<String> helloWorld(){
		return ResponseEntity.ok("Hello DashBoard");
	}
}
