package com.supera.test.services.exceptions.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public GameNotFoundException(String msg) {
		super(msg);
	}
}
