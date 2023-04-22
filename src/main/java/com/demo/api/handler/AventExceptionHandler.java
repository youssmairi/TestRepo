package com.demo.api.handler;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.api.exception.UnknownCategoryOrIsEmptyStockException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;

@RestControllerAdvice
public class AventExceptionHandler {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ProblemDetail handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
		ProblemDetail problemdetails = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
		return problemdetails;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ProblemDetail handleConstraintViolationException(ConstraintViolationException exception) {
		if (exception.getConstraintViolations() == null) {
			return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
		}
		Optional<ConstraintViolation<?>> constr = exception.getConstraintViolations().stream().findFirst();
		ProblemDetail problemdetails = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		problemdetails.setProperty("propertyPath",
				constr.map(ConstraintViolation::getPropertyPath).map(Path::toString).orElse(""));
		problemdetails.setProperty("invalidValue", constr.map(ConstraintViolation::getInvalidValue).orElse(""));
		problemdetails.setProperty("messageTemplate", constr.map(ConstraintViolation::getMessageTemplate).orElse(""));
		problemdetails.setStatus(HttpStatus.BAD_REQUEST);
		return problemdetails;

	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ProblemDetail handleUnknownCategoryOrIsEmptyStockException(UnknownCategoryOrIsEmptyStockException exception) {
		ProblemDetail problemdetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
		problemdetails.setProperty("categry id", exception.getId());
		return problemdetails;
	}

}