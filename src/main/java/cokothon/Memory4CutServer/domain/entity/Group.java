package cokothon.Memory4CutServer.domain.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@Table(name = "`group`")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Group extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String inviteCode;

	@OneToMany(fetch = FetchType.EAGER)
	private List<MemberMission> achievedList = new ArrayList<>();

	@Builder
	public Group(String name, int achievedStatus, String inviteCode) {
		this.name = name;
		this.inviteCode = inviteCode;
	}

	public void addAchieveMission(MemberMission mission) {
		this.achievedList.add(mission);
	}

	public void clearMissons() {
		this.achievedList.clear();
	}
}
