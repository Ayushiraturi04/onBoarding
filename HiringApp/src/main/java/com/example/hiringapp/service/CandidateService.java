package com.example.hiringapp.service;


import com.example.hiringapp.dto.BankDTO;
import com.example.hiringapp.dto.CandidateDTO;
import com.example.hiringapp.dto.EducationDTO;
import com.example.hiringapp.dto.PersonalDTO;
import com.example.hiringapp.entity.BankEntity;
import com.example.hiringapp.entity.CandidateEntity;
import com.example.hiringapp.entity.EducationEntity;
import com.example.hiringapp.entity.PersonalEntity;
import com.example.hiringapp.exception.BadRequestException;
import com.example.hiringapp.exception.CandidateNotFoundException;
import com.example.hiringapp.mappers.BankMapper;
import com.example.hiringapp.mappers.CandidateMapper;
import com.example.hiringapp.mappers.EducationMapper;
import com.example.hiringapp.mappers.PersonalMapper;
import com.example.hiringapp.repository.BankRepo;
import com.example.hiringapp.repository.CandidateRepo;
import com.example.hiringapp.repository.EducationRepo;
import com.example.hiringapp.repository.PersonalRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepo crepo;
    private final PersonalRepo prepo;
    private final EducationRepo edrepo;
    private final BankRepo brepo;

    private final CandidateMapper candidateMapper;
    private final PersonalMapper personalMapper;
    private final BankMapper bankMapper;
    private final EducationMapper educationMapper;

    public CandidateDTO addUser(CandidateDTO dto) {
        CandidateEntity candidate = candidateMapper.toEntity(dto);
        candidate.setStatus(CandidateEntity.Status.APPLIED);
        candidate.setOnboardStatus(CandidateEntity.OnboardStatus.NOT_STARTED);
        candidate = crepo.save(candidate);
        return candidateMapper.toDTO(candidate);
    }

    public CandidateDTO updateUser(Long id, @Valid CandidateDTO dto) {
        CandidateEntity existing = crepo.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));
        candidateMapper.updateCandidateFromDto(dto, existing);
        CandidateEntity updatedCandidate = crepo.save(existing);
        return candidateMapper.toDTO(updatedCandidate);
    }

    public void addPersonalInfo(Long id, PersonalDTO dto) {
        CandidateEntity candidate = crepo.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));
        PersonalEntity personal = personalMapper.toEntity(dto);
        personal.setCandidate(candidate);
        prepo.save(personal);
    }

    public void addBankInfo(Long id, BankDTO dto) {
        CandidateEntity candidate = crepo.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));
        BankEntity bank = bankMapper.toEntity(dto);
        bank.setCandidate(candidate);
        brepo.save(bank);
    }

    public void addEducationInfo(Long id, EducationDTO dto) {
        CandidateEntity candidate = crepo.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));
        EducationEntity education = educationMapper.toEntity(dto);
        education.setCandidate(candidate);
        edrepo.save(education);
    }

    private boolean isValidStatus(CandidateEntity.Status current, CandidateEntity.Status updated) {
        return switch (current) {
            case APPLIED -> updated == CandidateEntity.Status.INTERVIEWED;
            case INTERVIEWED -> updated == CandidateEntity.Status.OFFERED;
            case OFFERED -> updated == CandidateEntity.Status.ONBOARDED;
            default -> false;
        };
    }

    public void updateStatus(Long id, CandidateDTO dto) {
        CandidateEntity candidate = crepo.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));
        CandidateEntity.Status newStatus = CandidateEntity.Status.valueOf(String.valueOf(dto.getStatus()));
        if (!isValidStatus(candidate.getStatus(), newStatus)) {
            throw new BadRequestException("Invalid status transition");
        }
        candidate.setStatus(newStatus);
        crepo.save(candidate);
    }

    public void onboardingStatus(Long id, String onboardStatus) {
        CandidateEntity candidate = crepo.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));
        CandidateEntity.OnboardStatus status = CandidateEntity.OnboardStatus.valueOf(String.valueOf(candidate.getStatus()));
        candidate.setOnboardStatus(status);
        crepo.save(candidate);
    }

    public Long getCandidateCount() {
        return crepo.count();
    }

    public CandidateDTO viewCandidate(Long id) {
        CandidateEntity candidate = crepo.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));
        return candidateMapper.toDTO(candidate);
    }
}
