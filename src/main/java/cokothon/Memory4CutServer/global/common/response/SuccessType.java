package cokothon.Memory4CutServer.global.common.response;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum SuccessType {
	HEALTH_CHECK_SUCCESS(HttpStatus.OK, "헬스 체크에 성공했습니다.");

	private final HttpStatus httpStatus;
	private final String message;

	public final int getHttpStatusCode() {
		return httpStatus.value();
	}
}