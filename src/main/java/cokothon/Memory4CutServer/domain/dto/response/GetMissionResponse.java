package cokothon.Memory4CutServer.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import cokothon.Memory4CutServer.domain.entity.Mission;

public record GetMissionResponse(
	@JsonProperty("mission_content") String missionContent,
	@JsonProperty("mission_tense_type") String missionTenseType,
	@JsonProperty("achieve_status") int achieveStatus
) {

	public static GetMissionResponse of(Mission mission, int achieveStatus) {
		return new GetMissionResponse(
			mission.getContent(),
			mission.getTenseType().getType(),
			achieveStatus
		);
	}
}
