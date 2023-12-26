package cokothon.Memory4CutServer.global.common.response;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum SuccessType {

	/**
	 * 200 Ok
	 */
	HEALTH_CHECK_SUCCESS(HttpStatus.OK, "헬스 체크에 성공했습니다."),
	GET_INVITE_CODE_SUCCESS(HttpStatus.OK, "랜덤 초대코드 조회에 성공했습니다."),

	/**
	 * 201 Created
	 */
	GROUP_CREATE_SUCCESS(HttpStatus.CREATED, "그룹 생성에 성공했습니다."),
	GROUP_JOIN_SUCCESS(HttpStatus.CREATED, "그룹 조인에 성공했습니다.")


	;

	private final HttpStatus httpStatus;
	private final String message;

	public final int getHttpStatusCode() {
		return httpStatus.value();
	}
}
