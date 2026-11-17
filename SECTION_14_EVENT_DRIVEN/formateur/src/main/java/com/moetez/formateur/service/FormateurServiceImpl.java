package com.moetez.formateur.service;

import com.moetez.formateur.dto.APIResponseDto;
import com.moetez.formateur.dto.CoursDto;
import com.moetez.formateur.dto.FormateurDto;
import com.moetez.formateur.dto.FormateurMessageDto;
import com.moetez.formateur.entities.Formateur;
import com.moetez.formateur.exceptions.ResourceNotFoundException;
import com.moetez.formateur.repos.FormateurRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FormateurServiceImpl implements FormateurService {

    private static final Logger log = LoggerFactory.getLogger(FormateurServiceImpl.class);

    private final FormateurRepository formateurRepository;
    private final APIClient apiClient;
    private final StreamBridge streamBridge;

    @Override
    public APIResponseDto getFormateurById(Long id) {
        Formateur formateur = formateurRepository.findById(id).get();

        CoursDto coursDto = apiClient.getCoursByCode(formateur.getCodeCours());
        String nomCours = (coursDto == null) ? "NOT AVAILABLE" : coursDto.getNomCours();

        FormateurDto formateurDto = new FormateurDto(
                formateur.getId(),
                formateur.getNom(),
                formateur.getPrenom(),
                formateur.getCodeCours(),
                nomCours,
                formateur.getEmail(),
                formateur.getMobileNumber(),
                formateur.isCommunicationDone()
        );

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setFormateurDto(formateurDto);
        apiResponseDto.setCoursDto(coursDto);
        return apiResponseDto;
    }

    @Override
    public void createFormateur(FormateurDto formateurDto) {
        Formateur saved = formateurRepository.save(Formateur.builder()
                .nom(formateurDto.getNom())
                .prenom(formateurDto.getPrenom())
                .codeCours(formateurDto.getCodeCours())
                .email(formateurDto.getEmail())
                .mobileNumber(formateurDto.getMobileNumber())
                .build());
        sendCommunication(saved);
    }

    private void sendCommunication(Formateur formateur) {
        var message = new FormateurMessageDto(
                formateur.getId(),
                formateur.getEmail(),
                formateur.getMobileNumber()
        );
        log.info("Sending Communication request for the details: {}", message);
        var result = streamBridge.send("sendCommunication-out-0", message);
        log.info("Is the Communication request successfully triggered? : {}", result);
    }

    @Override
    public boolean updateCommunicationStatus(Long formateurId) {
        if (formateurId == null) {
            return false;
        }

        Formateur formateur = formateurRepository.findById(formateurId).orElseThrow(
                () -> new ResourceNotFoundException("Formateur", "FormateurId", formateurId.toString()));
        formateur.setCommunicationDone(true);
        formateurRepository.save(formateur);
        return true;
    }
}