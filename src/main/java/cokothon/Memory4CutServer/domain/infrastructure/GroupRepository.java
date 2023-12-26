package cokothon.Memory4CutServer.domain.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cokothon.Memory4CutServer.domain.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

	Optional<Group> findByInviteCode(String inviteCode);
}
