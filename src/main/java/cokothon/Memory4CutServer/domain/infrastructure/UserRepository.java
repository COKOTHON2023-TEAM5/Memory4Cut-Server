package cokothon.Memory4CutServer.domain.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import cokothon.Memory4CutServer.domain.entity.Member;

public interface UserRepository extends JpaRepository<Member, Long> {
}