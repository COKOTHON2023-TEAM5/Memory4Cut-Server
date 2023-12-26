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
	INVALID_IMAGE_EXTENSION(HttpStatus.BAD_REQUEST, "이미지 확장자는 jpeg, png, jpg, wepb만 가능합니다."),
	INVALID_IMAGE_SIZE(HttpStatus.BAD_REQUEST, "이미지 사이즈는 5MB를 넘을 수 없습니다."),

	/**
	 * 404 Not Found
	 */
	NOT_FOUND_GROUP(HttpStatus.NOT_FOUND, "존재하지 않는 그룹입니다."),

	/**
	 * 500 Internal Server Error
	 */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다");


	private final HttpStatus httpStatus;
	private final String message;

	public final int getHttpStatusCode() {
		return httpStatus.value();
	}
}
