package com.example.hiringapp.repository;

import com.example.hiringapp.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepo extends JpaRepository<BankEntity, Long> {
}
