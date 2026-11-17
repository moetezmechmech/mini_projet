package com.moetez.formateur.functions;

import com.moetez.formateur.service.FormateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class FormateurFunctions {

    private static final Logger log = LoggerFactory.getLogger(FormateurFunctions.class);

    @Bean
    public Consumer<Long> updateCommunication(FormateurService formateurService) {
        return formateurId -> {
            log.info("Updating Communication status for the formateur Id : {}", formateurId);
            formateurService.updateCommunicationStatus(formateurId);
        };
    }
}
