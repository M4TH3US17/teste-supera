package com.supera.test.services.exceptions.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemCarrinhoNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public ItemCarrinhoNotFoundException(String msg) {
		super(msg);
	}
}
