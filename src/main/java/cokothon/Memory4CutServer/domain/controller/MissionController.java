package cokothon.Memory4CutServer.domain.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cokothon.Memory4CutServer.domain.dto.request.AchieveMissionRequest;
import cokothon.Memory4CutServer.domain.dto.response.GetGroupPhotoResponse;
import cokothon.Memory4CutServer.domain.dto.response.GetMissionResponse;
import cokothon.Memory4CutServer.domain.service.MissionService;
import cokothon.Memory4CutServer.global.common.response.ApiResponse;
import cokothon.Memory4CutServer.global.common.response.SuccessType;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/group/{groupId}/mission")
@RequiredArgsConstructor
public class MissionController {

	private final MissionService missionService;

	@PatchMapping("/upload")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<GetGroupPhotoResponse> uploadMissionAchieve(
			@RequestPart MultipartFile image,
			@RequestBody AchieveMissionRequest request,
			@PathVariable Long groupId) {

		return ApiResponse.success(SuccessType.UPLOAD_MISSION_ACHIEVE_SUCCESS, missionService.achieveMission(request, image, groupId));
	}

	@PatchMapping
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<GetMissionResponse> getNewMission(@PathVariable Long groupId) {
		return ApiResponse.success(SuccessType.GET_NEW_MISSION_SUCCESS, missionService.getNewMission(groupId));
	}

	@PatchMapping
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<GetMissionResponse> chanegeMission(@PathVariable Long groupId) {
		return ApiResponse.success(SuccessType.CHANGE_MISSION_SUCCESS, missionService.changeMission(groupId));
	}
}
