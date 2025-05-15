package com.example.hiringapp.repository;

import com.example.hiringapp.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepo extends JpaRepository<DocumentEntity,Long> {
}
