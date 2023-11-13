package com.hoangtien2k3.productservice.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hoangtien2k3.productservice.exception.payload.ExceptionMsg;
import com.hoangtien2k3.productservice.exception.wrapper.CategoryNotFoundException;
import com.hoangtien2k3.productservice.exception.wrapper.ProductNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ApiExceptionHandler {
	
	@ExceptionHandler(value = {
		MethodArgumentNotValidException.class,
		HttpMessageNotReadableException.class,
	})
	public <T extends BindException> ResponseEntity<ExceptionMsg> handleValidationException(final T e) {
		log.info("ApiExceptionHandler controller, handle validation exception");
		final var badRequest = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<>(
				ExceptionMsg.builder()
					.message(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage())
					.httpStatus(badRequest)
					.timestamp(ZonedDateTime
							.now(ZoneId.systemDefault()))
					.build(), badRequest);
	}
	
	@ExceptionHandler(value = {
		CategoryNotFoundException.class,
		ProductNotFoundException.class,
	})
	public <T extends RuntimeException> ResponseEntity<ExceptionMsg> handleApiRequestException(final T e) {
		log.info("ApiExceptionHandler controller, handle API request");
		final var badRequest = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<>(
				ExceptionMsg.builder()
					.message(e.getMessage())
					.httpStatus(badRequest)
					.timestamp(ZonedDateTime
							.now(ZoneId.systemDefault()))
					.build(), badRequest);
	}
	
}










