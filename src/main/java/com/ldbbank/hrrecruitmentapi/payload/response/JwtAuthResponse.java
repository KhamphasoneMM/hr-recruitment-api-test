package com.ldbbank.hrrecruitmentapi.payload.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ldbbank.hrrecruitmentapi.entity.Roles;
import lombok.Data;

import java.util.Set;

@Data
public class JwtAuthResponse {
    private String token;
    private long expiresIn;
    private Set<Roles> userRole;
}
