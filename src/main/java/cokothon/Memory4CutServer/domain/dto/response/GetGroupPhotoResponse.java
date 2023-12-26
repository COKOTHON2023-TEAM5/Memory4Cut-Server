package cokothon.Memory4CutServer.domain.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import cokothon.Memory4CutServer.domain.entity.MemberMission;

public record GetGroupPhotoResponse(
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
