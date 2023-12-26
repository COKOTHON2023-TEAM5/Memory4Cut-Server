package cokothon.Memory4CutServer.domain.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cokothon.Memory4CutServer.domain.dto.request.AchieveMissionRequest;
import cokothon.Memory4CutServer.domain.dto.response.GetGroupPhotoResponse;
import cokothon.Memory4CutServer.domain.entity.Group;
import cokothon.Memory4CutServer.domain.entity.MemberMission;
import cokothon.Memory4CutServer.domain.entity.Mission;
import cokothon.Memory4CutServer.domain.infrastructure.GroupRepository;
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
	private final S3Service s3Service;

	@Transactional
	public GetGroupPhotoResponse achieveMission(AchieveMissionRequest request, MultipartFile image, Long groupId) {
		try {
			final String imgUrl = s3Service.uploadImage(MISSON_IMAGE_FOLDER_NAME, image);
			Group group = groupRepository.findById(groupId).orElseThrow(
				() -> new BaseException(ErrorType.NOT_FOUND_GROUP)
			);

			if (group.getAchievedList().size() == 4) {
				return GetGroupPhotoResponse.of(group.getAchievedList());
			}

			MemberMission memberMission = MemberMission.builder()
				.isAchieve(true)
				.imgUrl(imgUrl)
				.mission(existsMissionByGroup(group))
				.nickname(request.nickname())
				.build();
			group.addAchieveMission(memberMission);
			return GetGroupPhotoResponse.of(null);
		} catch (RuntimeException | IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private Mission existsMissionByGroup(Group group) {

		if (group.getAchievedList().isEmpty()) {
			return missionRepository.getRandomTodayMission();
		}
		return group.getAchievedList().get(0).getMission();
	}
}
