package com.example.hiringapp.service;


import com.example.hiringapp.dto.DocumentDTO;
import com.example.hiringapp.entity.CandidateEntity;
import com.example.hiringapp.entity.DocumentEntity;
import com.example.hiringapp.exception.CandidateNotFoundException;
import com.example.hiringapp.mappers.DocumentMapper;
import com.example.hiringapp.repository.CandidateRepo;
import com.example.hiringapp.repository.DocumentRepo;
import com.example.hiringapp.util.FileStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepo documentRepo;
    private final CandidateRepo candidateRepo;
    private final DocumentMapper documentMapper;
    private final FileStorage fileStorage;

    public void uploadDoc(Long id, MultipartFile file, DocumentDTO dto) {
        // Validate file
        if (!file.getContentType().equals("application/pdf")) {
            throw new IllegalArgumentException("Only PDF files are allowed");
        }
        if (file.getSize() > 10 * 1024 * 1024) { // 10MB limit
            throw new IllegalArgumentException("File size exceeds 5MB limit");
        }

        // Validate candidate ID
        if (!id.equals(dto.getCandidateId())) {
            throw new IllegalArgumentException("Path candidate ID does not match DTO candidate ID");
        }

        CandidateEntity candidate = candidateRepo.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));


        String fileUrl = fileStorage.storeFiles(file);

        //mapping
        DocumentEntity document=documentMapper.toEntity(dto);

        //manually using builder
        document = DocumentEntity.builder()
                .candidate(candidate)
                .documentType(document.getDocumentType())
                .fileUrl(fileUrl)
                .verified(false)
                .build();
        documentRepo.save(document);

    }

    public void verified(Long id, Long documentId) {
        DocumentEntity document= documentRepo.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        if (!document.getCandidate().getId().equals(id)) {
            throw new RuntimeException("Document does not belong to candidate with ID " + id);
        }
        document.setVerified(true);
        documentRepo.save(document);

    }
}
