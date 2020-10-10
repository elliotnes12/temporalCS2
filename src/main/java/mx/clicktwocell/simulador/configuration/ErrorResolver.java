package mx.clicktwocell.simulador.configuration;


import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import mx.clicktwocell.simulador.constants.LogValues;
import mx.clicktwocell.simulador.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import mx.clicktwocell.simulador.constants.Constants;
import mx.clicktwocell.simulador.response.ErrorResponse;

@RestControllerAdvice
@Slf4j
public class ErrorResolver {

	@Autowired
	private Constants errorResolverConstants;

	private static void writeToLog(ErrorResponse errorResponse, Exception exception) {
		log.error(LogValues.ERROR_CODE, errorResponse.getCode());
		log.error(LogValues.ERROR_DETAILS, errorResponse.getDetails());
		log.error(LogValues.ERROR_LOCATION, errorResponse.getLocation());
		log.error(LogValues.ERROR_MESSAGE, errorResponse.getMessage());

		String message = Objects.isNull(exception) ? Constants.EMPTY_STRING
				: exception.getMessage();
		log.error(message, exception);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse resolveMethodArgumentNotValidException(HttpServletRequest req,
			MethodArgumentNotValidException ex) {
		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setCode("400");

		Map<String, List<String>> groupedErrors = new HashMap<>();
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			String message = fieldError.getDefaultMessage();
			String field = fieldError.getField();
			groupedErrors.computeIfAbsent(message, key -> Collections.singletonList(field));
		}

		if (!groupedErrors.isEmpty()) {
			errorResponse.setDetails(groupedErrors.toString());
		}
		errorResponse.setLocation(req.getRequestURI());
		errorResponse.setMessage(errorResolverConstants.getInvalidParamsMessage());
		errorResponse.setTimestamp(ZonedDateTime.now());

		ErrorResolver.writeToLog(errorResponse, ex);

		return errorResponse;
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
	public ErrorResponse resolveHttpRequestMethodNotSupportedException(HttpServletRequest req,
			HttpRequestMethodNotSupportedException ex) {
		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setCode(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()));
		errorResponse.setDetails(ex.getMessage());
		errorResponse.setTimestamp(ZonedDateTime.now());
		errorResponse.setMessage(errorResolverConstants.getInvalidRequest());
		errorResponse.setLocation(req.getRequestURI());

		ErrorResolver.writeToLog(errorResponse, ex);

		return errorResponse;
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse resolveHttpMediaTypeNotSupportedException(HttpServletRequest req,
			HttpMediaTypeNotSupportedException ex) {
		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
		errorResponse.setMessage(errorResolverConstants.getMediaTypeNotSupportedMessage());
		errorResponse.setDetails(ex.getMessage());
		errorResponse.setTimestamp(ZonedDateTime.now());
		errorResponse.setLocation(req.getRequestURI());

		ErrorResolver.writeToLog(errorResponse, ex);

		return errorResponse;
	}

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ErrorResponse resolveUnauthorizedException(HttpServletRequest req,
			UnauthorizedException ex) {
		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setCode(String.valueOf(HttpStatus.UNAUTHORIZED.value()));
		errorResponse.setMessage(errorResolverConstants.getUnauthorizedExceptionMessage());
		errorResponse.setDetails(ex.getMessage());
		errorResponse.setTimestamp(ZonedDateTime.now());
		errorResponse.setLocation(req.getRequestURI());

		ErrorResolver.writeToLog(errorResponse, ex);

		return errorResponse;
	}

}