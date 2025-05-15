package com.example.hiringapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="education_details")
public class EducationEntity {
    @OneToOne
    @JoinColumn(name = "candidate_id",  nullable = false)
    private CandidateEntity candidate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String highestQualification;
    private String university;
    private String passingYear;
}

