package cokothon.Memory4CutServer.domain.dto.response;

import cokothon.Memory4CutServer.domain.entity.MemberMission;

public record MemberMissionDto(
	String imgUrl,
	String nickname
) {

	public static MemberMissionDto of(MemberMission memberMission) {
		return new MemberMissionDto(
			memberMission.getImgUrl(),
			memberMission.getNickname()
		);
	}
}
