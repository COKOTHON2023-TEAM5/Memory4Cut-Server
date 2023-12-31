package cokothon.Memory4CutServer.global.common.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

	private final int status;
	private final String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T data;

	public static <T> ApiResponse<T> success(SuccessType successType, T data) {
		return new ApiResponse<>(successType.getHttpStatusCode(), successType.getMessage(), data);
	}

	public static ApiResponse success(SuccessType successType) {
		return new ApiResponse<>(successType.getHttpStatusCode(), successType.getMessage());
	}

	public static ApiResponse error(ErrorType errorType, String message) {
		return new ApiResponse<>(errorType.getHttpStatusCode(), message);
	}

	public static ApiResponse error(ErrorType errorType) {
		return new ApiResponse<>(errorType.getHttpStatusCode(), errorType.getMessage());
	}

	public static ApiResponse<Exception> error(ErrorType errorType, Exception e) {
		return new ApiResponse<>(errorType.getHttpStatusCode(), errorType.getMessage(), e);
	}

	public static ApiResponse<Map<String, String>> error(ErrorType errorType, Map<String, String> stringMap) {
		return new ApiResponse<>(errorType.getHttpStatusCode(), errorType.getMessage(), stringMap);
	}
}