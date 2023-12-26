package cokothon.Memory4CutServer.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record JoinGroupRequest(
	@JsonProperty("invite_code") String inviteCode
) {
}
