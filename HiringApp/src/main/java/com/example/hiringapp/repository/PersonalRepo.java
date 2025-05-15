package com.example.hiringapp.repository;

import com.example.hiringapp.entity.PersonalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRepo extends JpaRepository<PersonalEntity,Long> {
}
