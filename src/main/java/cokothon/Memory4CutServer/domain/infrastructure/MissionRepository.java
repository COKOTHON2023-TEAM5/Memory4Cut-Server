package cokothon.Memory4CutServer.domain.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import cokothon.Memory4CutServer.domain.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
