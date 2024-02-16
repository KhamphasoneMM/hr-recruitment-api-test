package com.ldbbank.hrrecruitmentapi.payload.request;

import com.ldbbank.hrrecruitmentapi.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private Set<Roles> roles;



}
