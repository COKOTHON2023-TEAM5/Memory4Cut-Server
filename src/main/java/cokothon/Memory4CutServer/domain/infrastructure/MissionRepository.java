package cokothon.Memory4CutServer.domain.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cokothon.Memory4CutServer.domain.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {

	@Query(value = "SELECT * FROM mission ORDER BY RAND() LIMIT 1", nativeQuery = true)
	Mission getRandomTodayMission();
}
