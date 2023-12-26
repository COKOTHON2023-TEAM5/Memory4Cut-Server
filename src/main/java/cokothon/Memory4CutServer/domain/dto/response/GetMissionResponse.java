package cokothon.Memory4CutServer.domain.dto.response;

import cokothon.Memory4CutServer.domain.entity.Mission;

public record GetMissionResponse(
	String missionContent,
	String missionTenseType,
	int achieveStatus
) {

	public static GetMissionResponse of(Mission mission, int achieveStatus) {
		return new GetMissionResponse(
			mission.getContent(),
			mission.getTenseType().getType(),
			achieveStatus
		);
	}
}
