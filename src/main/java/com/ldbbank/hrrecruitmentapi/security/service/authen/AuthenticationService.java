package com.ldbbank.hrrecruitmentapi.security.service.authen;

import com.ldbbank.hrrecruitmentapi.entity.Users;
import com.ldbbank.hrrecruitmentapi.payload.request.LoginRequest;
import com.ldbbank.hrrecruitmentapi.payload.request.RegisterRequest;
import com.ldbbank.hrrecruitmentapi.payload.response.JwtAuthResponse;
import com.ldbbank.hrrecruitmentapi.payload.response.ResponseMessage;

public interface AuthenticationService {
    Users register(RegisterRequest request);

    JwtAuthResponse login(LoginRequest request);


}
