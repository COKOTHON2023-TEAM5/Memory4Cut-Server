package cokothon.Memory4CutServer.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import cokothon.Memory4CutServer.domain.entity.MemberMission;

public record MemberMissionDto(
	@JsonProperty("img_url") String imgUrl,
	String nickname
) {

	public static MemberMissionDto of(MemberMission memberMission) {
		return new MemberMissionDto(
			memberMission.getImgUrl(),
			memberMission.getNickname()
		);
	}
}
