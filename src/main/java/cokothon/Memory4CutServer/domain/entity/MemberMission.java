package cokothon.Memory4CutServer.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberMission extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_mission_id")
	private Long id;

	@Column(nullable = false)
	private boolean isAchieve = false;

	private String imgUrl;

	@OneToOne
	@JoinColumn(name = "mission_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Mission mission;

	@Builder
	public MemberMission(boolean isAchieve, String imgUrl, Mission mission) {
		this.isAchieve = isAchieve;
		this.imgUrl = imgUrl;
		this.mission = mission;
	}
}
