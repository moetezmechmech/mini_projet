package com.moetez.formateur.service;

import com.moetez.formateur.dto.CoursDto;
import org.springframework.stereotype.Component;

@Component
public class CoursFallBack implements APIClient {

    @Override
    public CoursDto getCoursByCode(String coursCode) {
        return null;
    }
}
