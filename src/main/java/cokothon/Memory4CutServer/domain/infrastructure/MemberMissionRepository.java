package cokothon.Memory4CutServer.domain.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import cokothon.Memory4CutServer.domain.entity.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
}
