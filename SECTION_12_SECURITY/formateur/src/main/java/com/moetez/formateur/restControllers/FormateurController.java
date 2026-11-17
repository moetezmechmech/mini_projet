package com.moetez.formateur.restControllers;

import com.moetez.formateur.config.Configuration;
import com.moetez.formateur.dto.APIResponseDto;
import com.moetez.formateur.service.FormateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/formateurs")
@RequiredArgsConstructor
public class FormateurController {

    private final FormateurService formateurService;
    private final Configuration configuration;

    @GetMapping("/{id}")
    public ResponseEntity<APIResponseDto> getFormateurById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(formateurService.getFormateurById(id), HttpStatus.OK);
    }

    @GetMapping("/author")
    public ResponseEntity<String> retrieveAuthorInfo() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(configuration.getName() + " " + configuration.getEmail());
    }
}