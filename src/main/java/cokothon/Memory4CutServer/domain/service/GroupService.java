package cokothon.Memory4CutServer.domain.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cokothon.Memory4CutServer.domain.dto.request.JoinGroupRequest;
import cokothon.Memory4CutServer.domain.dto.request.PostGroupRequest;
import cokothon.Memory4CutServer.domain.dto.response.GetGroupPhotoResponse;
import cokothon.Memory4CutServer.domain.dto.response.GetInviteCodeResponse;
import cokothon.Memory4CutServer.domain.entity.Group;
import cokothon.Memory4CutServer.domain.infrastructure.GroupRepository;
import cokothon.Memory4CutServer.global.common.exception.BaseException;
import cokothon.Memory4CutServer.global.common.response.ErrorType;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupService {

	private final GroupRepository groupRepository;

	@Transactional
	public void createGroup(PostGroupRequest request) {
		Group group = Group.builder()
			.name(request.groupName())
			.inviteCode(request.inviteCode())
			.build();
		groupRepository.save(group);
	}

	public GetInviteCodeResponse generateInviteCode() {
		return new GetInviteCodeResponse(RandomStringUtils.randomAlphabetic(4).toUpperCase() +
			"-" + RandomStringUtils.randomAlphanumeric(6));
	}

	public void joinGroup(JoinGroupRequest request) {
		groupRepository.findByInviteCode(request.inviteCode()).orElseThrow(
			() -> new BaseException(ErrorType.NOT_FOUND_GROUP_BY_CODE)
		);
	}

	public GetGroupPhotoResponse getFourCutPhoto(Long groupId) {
		Group group = groupRepository.findById(groupId).orElseThrow(
			() -> new BaseException(ErrorType.NOT_FOUND_GROUP)
		);
		if (group.achieveCount() != 4) {
			throw new BaseException(ErrorType.NOT_YET_ACHIEVE_FOUR_USERS);
		}

		return GetGroupPhotoResponse.of(group.getAchievedList());
	}
}
