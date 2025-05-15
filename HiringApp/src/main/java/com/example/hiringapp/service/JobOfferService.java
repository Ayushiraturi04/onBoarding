package com.example.hiringapp.service;




import com.example.hiringapp.config.RabbitMqConfig;
import com.example.hiringapp.entity.CandidateEntity;
import com.example.hiringapp.entity.JobOfferNotification;
import com.example.hiringapp.exception.CandidateNotFoundException;
import com.example.hiringapp.repository.CandidateRepo;
import com.example.hiringapp.repository.JobOfferRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobOfferService {

    private final CandidateRepo candidateRepo;
    private final RabbitTemplate rabbitTemplate;
    private final JobOfferRepo jobOfferRepository;

    public void notify(Long candidateId, String position) {
        CandidateEntity candidate = candidateRepo.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException(candidateId));
        JobOfferNotification notification = JobOfferNotification.builder()
                .candidate(candidate)
                .sent(false)
                .retryCount(0)
                .build();
        jobOfferRepository.save(notification);

        rabbitTemplate.convertAndSend(RabbitMqConfig.JOB_OFFER_QUEUE,candidateId);
    }


}