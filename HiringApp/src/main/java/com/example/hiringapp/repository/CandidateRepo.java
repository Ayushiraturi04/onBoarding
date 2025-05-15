package com.example.hiringapp.repository;

import com.example.hiringapp.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepo extends JpaRepository<CandidateEntity,Long> {
}
