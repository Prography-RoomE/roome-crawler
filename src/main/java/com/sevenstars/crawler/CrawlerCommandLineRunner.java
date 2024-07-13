package com.sevenstars.crawler;

import com.sevenstars.crawler.domain.ColoryCrawler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CrawlerCommandLineRunner implements CommandLineRunner {

    private final ColoryCrawler coloryCrawler;

    @Override
    public void run(String... args) {
        coloryCrawler.get();
    }
}
