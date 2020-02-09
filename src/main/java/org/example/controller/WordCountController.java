package org.example.controller;

import org.example.service.WordStatsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/top-3-words")
public class WordCountController {

    private final WordStatsService wordStatsService;

    public WordCountController(WordStatsService wordStatsService) {
        this.wordStatsService = wordStatsService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public List<String> getTop3Words(InputStream inputStream) {
        var top3Words = wordStatsService.top_3_words(inputStream);
        return List.of(top3Words);
    }
}
