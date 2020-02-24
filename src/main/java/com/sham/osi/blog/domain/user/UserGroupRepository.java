package com.sham.osi.blog.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

	Optional<UserGroup> findByAuthority(RoleEnum roleEnum);

}
