package com.sevenstars.crawler.domain.repository;

import com.sevenstars.crawler.domain.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
}
