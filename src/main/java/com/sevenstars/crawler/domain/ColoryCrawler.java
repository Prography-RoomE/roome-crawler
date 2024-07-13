package com.sevenstars.crawler.domain;

import com.sevenstars.crawler.global.api.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@RequiredArgsConstructor
@Component
public class ColoryCrawler {

    private static final String SERVER_URL = "https://colory.mooo.com/bba/catalogue";
    private final ApiService apiService;

    public void get() {
        try {
            Document document = apiService.get(SERVER_URL);
            parse(document);
        } catch (Exception exception) {
            log.error("Failed to read from server URL: {}", SERVER_URL, exception);
            throw new IllegalStateException("Read failed.", exception);
        }
    }

    private void parse(Document document) {
        Elements themesInfos = document.getElementsByClass("themes-info");

        for (Element themesInfo : themesInfos) {
            Elements selections = themesInfo.getElementsByClass("select-area");

            for (Element selection : selections) {
                String area = selection.text();
                Element table = selection.nextElementSibling();

                if (table != null && "table".equals(table.tagName())) {
                    processTable(area, table);
                }
            }
        }
    }

    private String getTextByClass(Element element, String className) {
        return element.getElementsByClass(className).stream()
                .findFirst()
                .map(Element::text)
                .orElse("");
    }

    private void processTable(String area, Element table) {
        Elements tableRows = table.select("tbody tr");
        String cafeName = "";

        for (Element tableRow : tableRows) {
            String name = getTextByClass(tableRow, "info-1");
            if (StringUtils.hasText(name)) {
                cafeName = name;
            }
            String themeName = getTextByClass(tableRow, "info-2");
            String score = getTextByClass(tableRow, "info-3");
            String difficulty = getTextByClass(tableRow, "info-4");
            String reviewCount = getTextByClass(tableRow, "info-5");

            log.info("{}\t{}\t{}\t{}\t{}\t{}", area, cafeName, themeName, score, difficulty, reviewCount);
        }
    }
}
