package com.ldbbank.hrrecruitmentapi.controller;

import com.ldbbank.hrrecruitmentapi.entity.Users;
import com.ldbbank.hrrecruitmentapi.payload.request.LoginRequest;
import com.ldbbank.hrrecruitmentapi.payload.request.RegisterRequest;
import com.ldbbank.hrrecruitmentapi.payload.response.JwtAuthResponse;
import com.ldbbank.hrrecruitmentapi.security.service.GetUserDetailService;
import com.ldbbank.hrrecruitmentapi.security.service.JwtService;
import com.ldbbank.hrrecruitmentapi.security.service.authen.AuthenticationService;
import com.ldbbank.hrrecruitmentapi.utils.ApiConstrants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("${url.mapping}" +ApiConstrants.API_VERSION + ApiConstrants.AUTH.MAIN_PATH)
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    public final GetUserDetailService getUserDetailService;

    public AuthController(JwtService jwtService, AuthenticationService authenticationService, GetUserDetailService getUserDetailService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.getUserDetailService = getUserDetailService;
    }

    @PostMapping(ApiConstrants.AUTH.REGISTER)
    public ResponseEntity<Users> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping( ApiConstrants.AUTH.LOGIN)
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping(ApiConstrants.AUTH.USER_DETAILS)
    public ResponseEntity<?> getUserDetail() {
        return ResponseEntity.ok(getUserDetailService.getUserDetail());
    }


}
