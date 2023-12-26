package cokothon.Memory4CutServer.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record JoinGroupRequest(

	@NotBlank(message = "초대코드는 필수 입력 값입니다.")
	@Pattern(regexp = "[A-Z]{4}-[A-Za-z0-9]{6}", message = "초대코드 형식에 맞지 않습니다.")
	@Size(max = 11)
	@JsonProperty("invite_code")
	String inviteCode
) {
}
