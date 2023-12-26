package cokothon.Memory4CutServer.global.common.exception;

import cokothon.Memory4CutServer.global.common.response.ErrorType;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

	private final ErrorType errorType;

	public BaseException(ErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}

	public int getHttpStatus() {
		return errorType.getHttpStatusCode();
	}
}
