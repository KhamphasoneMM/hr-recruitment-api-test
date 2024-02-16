package com.ldbbank.hrrecruitmentapi.repository;

import com.ldbbank.hrrecruitmentapi.entity.ApplicantInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantInfoRepository extends CrudRepository<ApplicantInfo, Long> {

}
