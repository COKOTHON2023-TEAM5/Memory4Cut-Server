package cokothon.Memory4CutServer.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GetInviteCodeResponse(

	@JsonProperty("invite_code") String inviteCode
) {
}
