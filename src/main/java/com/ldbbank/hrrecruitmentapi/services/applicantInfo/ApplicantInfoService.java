package com.ldbbank.hrrecruitmentapi.services.applicantInfo;

import com.ldbbank.hrrecruitmentapi.payload.request.ApplicantInfoRequest;
import com.ldbbank.hrrecruitmentapi.payload.response.ResponseMessage;

public interface ApplicantInfoService {
    ResponseMessage submitApplicantInfo(ApplicantInfoRequest request);
    ResponseMessage getApplicantInfo();
    ResponseMessage getApplicantInfoById(Long id);
    ResponseMessage updateApplicantInfoUpdateById(Long id, ApplicantInfoRequest request);
    ResponseMessage deleteApplicantInfoById(Long id);


}
