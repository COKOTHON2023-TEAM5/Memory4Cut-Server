package cokothon.Memory4CutServer.global.common.response;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorType {

	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다");


	private final HttpStatus httpStatus;
	private final String message;

	public final int getHttpStatusCode() {
		return httpStatus.value();
	}
}
