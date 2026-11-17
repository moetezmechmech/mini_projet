package com.moetez.formateur.service;

import com.moetez.formateur.dto.APIResponseDto;
import com.moetez.formateur.dto.FormateurDto;

public interface FormateurService {
    APIResponseDto getFormateurById(Long id);
    void createFormateur(FormateurDto formateurDto);
    boolean updateCommunicationStatus(Long formateurId);
}