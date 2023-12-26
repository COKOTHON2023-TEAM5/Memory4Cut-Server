package cokothon.Memory4CutServer.global.common.advice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import cokothon.Memory4CutServer.global.common.exception.BaseException;
import cokothon.Memory4CutServer.global.common.response.ApiResponse;
import cokothon.Memory4CutServer.global.common.response.ErrorType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@Component
public class ControllerExceptionAdvice {

	/**
	 * 400 Bad Request
	 */

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ApiResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {

		Errors errors = e.getBindingResult();
		Map<String, String> validateDetails = new HashMap<>();

		for (FieldError error : errors.getFieldErrors()) {
			String validKeyName = String.format("valid_%s", error.getField());
			validateDetails.put(validKeyName, error.getDefaultMessage());
		}
		return ApiResponse.error(ErrorType.REQUEST_VALIDATION_EXCEPTION, validateDetails);
	}

	// 잘못된 타입으로 요청을 보낸 경우
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ApiResponse<Exception> handlerMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
		return ApiResponse.error(ErrorType.VALIDATION_WRONG_TYPE_EXCEPTION);
	}

	// Header에 원하는 Key가 없는 경우
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingRequestHeaderException.class)
	protected ApiResponse<Object> handlerMissingRequestHeaderException(final MissingRequestHeaderException e) {
		return ApiResponse.error(ErrorType.HEADER_REQUEST_MISSING_EXCEPTION);
	}

	// 잘못된 HTTP Method로 요청을 보낸 경우
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ApiResponse<Object> handlerHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
		return ApiResponse.error(ErrorType.INVALID_HTTP_METHOD);
	}

	/**
	 * 500 Internal Server Error
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(IndexOutOfBoundsException.class)
	protected ApiResponse<Exception> handlerIndexOutOfBoundsException(final IndexOutOfBoundsException e) {
		return ApiResponse.error(ErrorType.INDEX_OUT_OF_BOUNDS, e);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(IllegalArgumentException.class)
	public ApiResponse<Exception> handlerIllegalArgumentException(final IllegalArgumentException e) {
		return ApiResponse.error(ErrorType.INTERNAL_SERVER_ERROR, e);
	}

	/**
	 * CUSTOM_ERROR
	 */
	@ExceptionHandler(value = BaseException.class)
	protected ResponseEntity<ApiResponse> handleCustomException(BaseException e) {

		log.error("CustomException occured: {}", e.getMessage(), e);

		return ResponseEntity.status(e.getHttpStatus())
			.body(ApiResponse.error(e.getErrorType(), e.getMessage()));
	}
}
