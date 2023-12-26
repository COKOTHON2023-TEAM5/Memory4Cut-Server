package cokothon.Memory4CutServer.global.common.response;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorType {

	/**
	 * 400 Bad Request
	 */
	REQUEST_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "요청값의 형식이 올바르지 않습니다."),

	INVALID_IMAGE_EXTENSION(HttpStatus.BAD_REQUEST, "이미지 확장자는 jpeg, png, jpg, wepb만 가능합니다."),
	INVALID_IMAGE_SIZE(HttpStatus.BAD_REQUEST, "이미지 사이즈는 5MB를 넘을 수 없습니다."),
	INVALID_TRY_TO_CHANGE_MISSION(HttpStatus.BAD_REQUEST, "이미 인증한 유저가 존재하므로 미션을 새로고침할 수 없습니다."),

	NOT_YET_ACHIEVE_FOUR_USERS(HttpStatus.BAD_REQUEST, "아직 모든 유저가 미션을 달성하지 않았습니다."),
	INVALID_INVITE_CODE(HttpStatus.BAD_REQUEST, "유효하지 않은 초대코드입니다."),

	VALIDATION_WRONG_TYPE_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 타입이 입력되었습니다."),
	HEADER_REQUEST_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "요청에 필요한 헤더값이 존재하지 않습니다."),
	INVALID_HTTP_METHOD(HttpStatus.BAD_REQUEST, "지원되지 않는 HTTP Method 요청입니다."),


	/**
	 * 404 Not Found
	 */
	NOT_FOUND_GROUP(HttpStatus.NOT_FOUND, "존재하지 않는 그룹입니다."),

	/**
	 * 500 Internal Server Error
	 */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다"),
	INDEX_OUT_OF_BOUNDS(HttpStatus.INTERNAL_SERVER_ERROR, "인덱스 범위를 초과했습니다.");



	private final HttpStatus httpStatus;
	private final String message;

	public final int getHttpStatusCode() {
		return httpStatus.value();
	}
}
