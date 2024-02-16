package com.ldbbank.hrrecruitmentapi.repository;

import com.ldbbank.hrrecruitmentapi.entity.UserRoles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRoles, Long> {

}
