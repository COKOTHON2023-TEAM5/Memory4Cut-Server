package cokothon.Memory4CutServer.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostGroupRequest(
	@JsonProperty("group_name") String groupName,
	@JsonProperty("invite_code") String inviteCode
) {
}
