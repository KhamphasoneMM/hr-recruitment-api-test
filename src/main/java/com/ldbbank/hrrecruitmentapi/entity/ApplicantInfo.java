package com.ldbbank.hrrecruitmentapi.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@Entity
@Validated
@Table(name = "applicant_info")
public class ApplicantInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "interest_position", nullable = false)
    private String interest_position;
    @Column(name = "location_based", nullable = false)
    private String location_based;

    @Column(name = "education", nullable = false)
    private String education;
    @Column(name = "majoring", nullable = false)
    private String majoring;
    @Column(name = "work_exp", nullable = false)
    private String work_exp;
    @Column(name = "tell", nullable = false)
    private String tell;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "language", nullable = false)
    private String language;
    @Column(name = "video", nullable = false)
    private String video;
    @Column(name = "cv", nullable = false)
    private String cv;

    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp updated_at;


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
