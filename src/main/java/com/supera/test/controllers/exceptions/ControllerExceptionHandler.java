package com.supera.test.controllers.exceptions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.supera.test.services.exceptions.notfound.CarrinhoNotFoundException;
import com.supera.test.services.exceptions.notfound.ClienteNotFoundException;
import com.supera.test.services.exceptions.notfound.GameNotFoundException;
import com.supera.test.services.exceptions.notfound.ItemCarrinhoNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private static Instant timestamp = Instant.now();
	
	@ExceptionHandler({ClienteNotFoundException.class, 
		CarrinhoNotFoundException.class, GameNotFoundException.class, ItemCarrinhoNotFoundException.class })
	public ResponseEntity<ErrorDetails> exceptionNotFound(Exception e) {
		ErrorDetails erro = new ErrorDetails();
		erro.setStatus(HttpStatus.NOT_FOUND.value());
		erro.setTimestamp(timestamp.toString());
		erro.setError(e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	protected ResponseEntity<Map<String, ErrorDetails>> validacaoCamposException(MethodArgumentNotValidException ex) {
		Map<String, ErrorDetails> map = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldError = ((FieldError) error).getField();
			ErrorDetails err = new ErrorDetails();
			err.setError(error.getDefaultMessage());
			err.setTimestamp(timestamp.toString());
			err.setStatus(HttpStatus.BAD_REQUEST.value());
			map.put(fieldError, err);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
	}
	
	@ExceptionHandler({HttpMessageNotReadableException.class})
	public ResponseEntity<ErrorDetails> sintaxeJsonException(Exception e) {
		ErrorDetails erro = new ErrorDetails();
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setTimestamp(timestamp.toString());
		erro.setError("Erro de sintaxe no JSON.");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler({ConstraintViolationException.class, 
		DataIntegrityViolationException.class, EmptyResultDataAccessException.class})
	public ResponseEntity<ErrorDetails> violacaoForeignKey(Exception e) {
		ErrorDetails erro = new ErrorDetails();
		erro.setStatus(HttpStatus.CONFLICT.value());
		erro.setTimestamp(timestamp.toString());
		erro.setError("Violação de Foreign Key.");
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
}
