package com.ldbbank.hrrecruitmentapi.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Valid
public class ApplicantInfoRequest {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
    @JsonProperty("name")
    @NotEmpty(message = "Name may not be null")
    private String name;
    @JsonProperty("gender")
    @NotEmpty(message = "Gender may not be empty")
    private String gender;
    @JsonProperty("interest_position")
    @NotEmpty(message = "Interest position may not be empty")
    private String interest_position;
    @JsonProperty("location_based")
    @NotEmpty(message = "Location based may not be empty")
    private String location_based;
    @JsonProperty("education")
    @NotEmpty(message = "Education may not be empty")
    private String education;
    @JsonProperty("majoring")
    @NotEmpty(message = "Majoring may not be empty")
    private String majoring;
    @JsonProperty("work_exp")
    @NotEmpty(message = "Work experience may not be empty")
    private String work_exp;
    @JsonProperty("tell")
    @NotEmpty(message = "Phone number may not be empty")
    private String tell;
    @JsonProperty("email")
    @Email(message = "Email may not be empty")
    private String email;
    @JsonProperty("language")
    @NotEmpty(message = "Language may not be empty")
    private String language;
    @JsonProperty("video")
    @NotNull(message = "Video file may not be empty")
    private MultipartFile video;
    @JsonProperty("cv")
    @NotNull(message = "CV file may not be empty")
    private MultipartFile cv;


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
