package com.example.hiringapp.listener;


import com.example.hiringapp.config.RabbitMqConfig;
import com.example.hiringapp.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobNotificationListener {
    private final EmailService emailService;

    @RabbitListener(queues = RabbitMqConfig.JOB_OFFER_QUEUE)
    public void handleJobOffer(Long candidateId) {
        emailService.sendJobOffer(candidateId);
    }
}
