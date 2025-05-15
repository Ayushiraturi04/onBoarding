package com.example.hiringapp.repository;

import com.example.hiringapp.entity.EducationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepo extends JpaRepository<EducationEntity,Long> {
}
