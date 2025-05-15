package com.example.hiringapp.repository;

import com.example.hiringapp.entity.JobOfferNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobOfferRepo extends JpaRepository<JobOfferNotification,Long> {
}
