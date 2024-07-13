package com.sevenstars.crawler.domain.service;


import com.sevenstars.crawler.domain.entity.Theme;
import com.sevenstars.crawler.domain.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ThemeService {

    private final ThemeRepository themeRepository;

    @Transactional
    public void save(List<Theme> themes) {
        themeRepository.saveAll(themes);
    }
}
