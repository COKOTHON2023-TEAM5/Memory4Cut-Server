package cokothon.Memory4CutServer.global.common.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cokothon.Memory4CutServer.global.common.exception.BaseException;
import cokothon.Memory4CutServer.global.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@Component
public class ControllerExceptionAdvice {

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
