package org.example.service;

import java.io.InputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class WordStatsService {

    public String[] top_3_words(InputStream inputStream) {
        Map<String, Integer> wordCount = new HashMap<>();
        Pattern wordPattern = Pattern.compile("[^a-zA-Z']+");

        // Scanning an inputStream will enable us to process arbitrarily long inputs
        Scanner s = new Scanner(inputStream);
        s.useDelimiter(wordPattern);
        while (s.hasNext()) {
            String word = s.next().toLowerCase();
            Integer count = wordCount.computeIfAbsent(word, s1 -> 0);
            wordCount.put(word, count + 1);
        }

        Comparator<Map.Entry<String, Integer>> compareByCount = Map.Entry.comparingByValue();

        return wordCount.entrySet().stream()
                .sorted(compareByCount.reversed())
                .map(Map.Entry::getKey)
                .limit(3)
                .toArray(String[]::new);
    }
}
