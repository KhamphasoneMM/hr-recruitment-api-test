package com.ldbbank.hrrecruitmentapi.payload.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ldbbank.hrrecruitmentapi.entity.Roles;
import lombok.Data;

import java.util.Set;

@Data
public class UserResponse {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String permission;
    private  String is_active;
    private Set<Roles> roles ;


    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();

        String jsonString = "";
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            jsonString = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
