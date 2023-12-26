package cokothon.Memory4CutServer.domain.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cokothon.Memory4CutServer.domain.dto.request.JoinGroupRequest;
import cokothon.Memory4CutServer.domain.dto.request.PostGroupRequest;
import cokothon.Memory4CutServer.domain.dto.response.GetGroupPhotoResponse;
import cokothon.Memory4CutServer.domain.dto.response.GetInviteCodeResponse;
import cokothon.Memory4CutServer.domain.service.GroupService;
import cokothon.Memory4CutServer.global.common.response.ApiResponse;
import cokothon.Memory4CutServer.global.common.response.SuccessType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

	private final GroupService groupService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse createGroup(@Valid @RequestBody PostGroupRequest request) {
		groupService.createGroup(request);
		return ApiResponse.success(SuccessType.GROUP_CREATE_SUCCESS);
	}

	@GetMapping("/code")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<GetInviteCodeResponse> getInviteCode() {
		return ApiResponse.success(SuccessType.GET_INVITE_CODE_SUCCESS, groupService.generateInviteCode());
	}

	@PostMapping("/join")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse joinGroup(@Valid @RequestBody JoinGroupRequest request) {
		groupService.joinGroup(request);
		return ApiResponse.success(SuccessType.GROUP_JOIN_SUCCESS);
	}

	@GetMapping("/{groupId}/photo")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<GetGroupPhotoResponse> getGroupPhoto(@PathVariable Long groupId) {
		return ApiResponse.success(SuccessType.GET_GROUP_PHOTO_SUCCESS, groupService.getFourCutPhoto(groupId));
	}
}
