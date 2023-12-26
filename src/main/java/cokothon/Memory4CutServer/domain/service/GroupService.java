package cokothon.Memory4CutServer.domain.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cokothon.Memory4CutServer.domain.dto.request.JoinGroupRequest;
import cokothon.Memory4CutServer.domain.dto.request.PostGroupRequest;
import cokothon.Memory4CutServer.domain.dto.response.GetInviteCodeResponse;
import cokothon.Memory4CutServer.domain.entity.Group;
import cokothon.Memory4CutServer.domain.entity.Member;
import cokothon.Memory4CutServer.domain.infrastructure.GroupRepository;
import cokothon.Memory4CutServer.domain.infrastructure.MemberRepository;
import cokothon.Memory4CutServer.global.common.exception.BaseException;
import cokothon.Memory4CutServer.global.common.response.ErrorType;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupService {

	private final GroupRepository groupRepository;
	private final MemberRepository memberRepository;

	@Transactional
	public void createGroup(PostGroupRequest request) {
		Group group = Group.builder()
			.name(request.name())
			.achievedStatus(0)
			.inviteCode(request.inviteCode())
			.build();
		groupRepository.save(group);
	}

	public GetInviteCodeResponse generateInviteCode() {
		return new GetInviteCodeResponse(RandomStringUtils.randomAlphabetic(4).toUpperCase() +
			"-" + RandomStringUtils.randomAlphanumeric(6));
	}

	@Transactional
	public void joinGroup(JoinGroupRequest request) {
		Group group = groupRepository.findByInviteCode(request.inviteCode()).orElseThrow(
			() -> new BaseException(ErrorType.NOT_FOUND_GROUP)
		);

		Member member = new Member();
		memberRepository.save(member);
		group.addGroupMember(member);
	}
}
