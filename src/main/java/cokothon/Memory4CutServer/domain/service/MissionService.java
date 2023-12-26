package cokothon.Memory4CutServer.domain.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cokothon.Memory4CutServer.domain.dto.request.AchieveMissionRequest;
import cokothon.Memory4CutServer.domain.dto.response.GetGroupPhotoResponse;
import cokothon.Memory4CutServer.domain.dto.response.GetMissionResponse;
import cokothon.Memory4CutServer.domain.entity.Group;
import cokothon.Memory4CutServer.domain.entity.MemberMission;
import cokothon.Memory4CutServer.domain.entity.Mission;
import cokothon.Memory4CutServer.domain.infrastructure.GroupRepository;
import cokothon.Memory4CutServer.domain.infrastructure.MemberMissionRepository;
import cokothon.Memory4CutServer.domain.infrastructure.MissionRepository;
import cokothon.Memory4CutServer.global.common.exception.BaseException;
import cokothon.Memory4CutServer.global.common.response.ErrorType;
import cokothon.Memory4CutServer.global.external.S3Service;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionService {

	private static final String MISSON_IMAGE_FOLDER_NAME = "missions/";

	private final MissionRepository missionRepository;
	private final GroupRepository groupRepository;
	private final MemberMissionRepository memberMissionRepository;
	private final S3Service s3Service;

	@Transactional
	public GetGroupPhotoResponse achieveMission(AchieveMissionRequest request, MultipartFile image, Long groupId) {
		try {
			final String imgUrl = s3Service.uploadImage(MISSON_IMAGE_FOLDER_NAME, image);
			Group group = getGroupById(groupId);

			if (group.getAchievedList().size() == 4) {
				return GetGroupPhotoResponse.of(group.getAchievedList());
			}

			MemberMission memberMission = MemberMission.builder()
				.isAchieve(true)
				.imgUrl(imgUrl)
				.mission(existsMissionByGroup(group))
				.nickname(request.nickname())
				.build();
			memberMissionRepository.save(memberMission);

			group.addAchieveMission(memberMission);
			return GetGroupPhotoResponse.of(new ArrayList<MemberMission>());
		} catch (RuntimeException | IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Transactional
	public GetMissionResponse getNewMission(Long groupId) {

		Group group = getGroupById(groupId);
		Mission mission = existsMissionByGroup(group);
		MemberMission memberMission = MemberMission.builder()
			.isAchieve(false)
			.mission(mission)
			.build();
		memberMissionRepository.save(memberMission);

		group.getAchievedList().add(memberMission);
		return GetMissionResponse.of(mission, group.achieveCount());
	}

	@Transactional
	public GetMissionResponse changeMission(Long groupId) {

		Group group = getGroupById(groupId);

		if (group.achieveCount() >= 1) {
			throw new BaseException(ErrorType.INVALID_TRY_TO_CHANGE_MISSION);
		}
		Mission mission = missionRepository.getRandomNewMission(group.getAchievedList().get(0).getMission().getId());
		group.changeMission(mission);

		return GetMissionResponse.of(mission, group.achieveCount());
	}

	private Group getGroupById(Long id) {
		return groupRepository.findById(id).orElseThrow(
			() -> new BaseException(ErrorType.NOT_FOUND_GROUP)
		);
	}

	private Mission existsMissionByGroup(Group group) {

		if (group.getAchievedList().isEmpty()) {
			return missionRepository.getRandomTodayMission();
		}
		return group.getAchievedList().get(0).getMission();
	}
}
