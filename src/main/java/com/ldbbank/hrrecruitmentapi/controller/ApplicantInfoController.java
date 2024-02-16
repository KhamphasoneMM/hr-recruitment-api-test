package com.ldbbank.hrrecruitmentapi.controller;

import com.ldbbank.hrrecruitmentapi.entity.Users;
import com.ldbbank.hrrecruitmentapi.payload.request.ApplicantInfoRequest;
import com.ldbbank.hrrecruitmentapi.payload.request.RegisterRequest;
import com.ldbbank.hrrecruitmentapi.payload.response.ResponseMessage;
import com.ldbbank.hrrecruitmentapi.services.applicantInfo.ApplicantInfoService;
import com.ldbbank.hrrecruitmentapi.utils.ApiConstrants;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("${url.mapping}" + ApiConstrants.API_VERSION)
@Validated
public class ApplicantInfoController  {
    @Autowired
    ApplicantInfoService applicantInfoService;

    @PostMapping(ApiConstrants.APPLICANT_INFO.APPLICANT_INFO_SUBMIT)
    public ResponseEntity<ResponseMessage> submitApplicantInfo(@Valid @ModelAttribute ApplicantInfoRequest request, BindingResult errors) {//this is the validation barrier
        if (errors.hasErrors()) {
            throw new ValidationException((Throwable) errors);
        }

        return ResponseEntity.ok(applicantInfoService.submitApplicantInfo(request));
    }


    @GetMapping(ApiConstrants.APPLICANT_INFO.APPLICANT_INFO_ALL)
    public ResponseEntity<ResponseMessage> getApplicantInfo() {
        return ResponseEntity.ok(applicantInfoService.getApplicantInfo());
    }
    @GetMapping(ApiConstrants.APPLICANT_INFO.APPLICANT_INFO_BY_ID)
    public ResponseEntity<ResponseMessage> getApplicantInfoById(@PathVariable Long id) {
        return ResponseEntity.ok(applicantInfoService.getApplicantInfoById(id));
    }
    @PutMapping(ApiConstrants.APPLICANT_INFO.APPLICANT_INFO_UPDATE)
    public ResponseEntity<ResponseMessage> updateApplicantInfoUpdateById(@PathVariable Long id, @RequestBody ApplicantInfoRequest request) {
        return ResponseEntity.ok(applicantInfoService.updateApplicantInfoUpdateById(id, request));
    }
    @DeleteMapping(ApiConstrants.APPLICANT_INFO.APPLICANT_INFO_DELETE)
    public ResponseEntity<ResponseMessage> deleteApplicantInfoById(@PathVariable Long id) {
        return ResponseEntity.ok(applicantInfoService.deleteApplicantInfoById(id));
    }

}
