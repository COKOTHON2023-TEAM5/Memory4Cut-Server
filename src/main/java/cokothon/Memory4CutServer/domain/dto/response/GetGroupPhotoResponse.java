package cokothon.Memory4CutServer.domain.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import cokothon.Memory4CutServer.domain.entity.MemberMission;

public record GetGroupPhotoResponse(

	@JsonProperty("achieve_mission_list")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	List<MemberMissionDto> achieveMissionList
) {

	public static GetGroupPhotoResponse of(List<MemberMission> memberMissionList) {
		return new GetGroupPhotoResponse(
			memberMissionList.stream().map(
				MemberMissionDto::of
			).toList());
	}
}
