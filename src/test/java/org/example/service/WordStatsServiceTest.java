package org.example.service;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class WordStatsServiceTest {

    private WordStatsService underTest;

    @Before
    public void setUp() {
        underTest = new WordStatsService();
    }

    @Test
    public void empty_output_if_empty_input() {
        var empty = toInputStream("");
        var top3Words = underTest.top_3_words(empty);
        assertThat(top3Words, is(emptyArray()));
    }

    @Test
    public void one_word_sentence_returns_that_word() {
        var singleWord = toInputStream("    hello ");
        var top3Words = underTest.top_3_words(singleWord);
        assertThat(top3Words, is(arrayContaining("hello")));
    }

    @Test
    public void case_is_ignored() {
        var repeatedWord = toInputStream("Bye bye");
        var top3Words = underTest.top_3_words(repeatedWord);
        assertThat(top3Words, is(arrayContaining("bye")));
    }

    @Test
    public void newlines_and_tabs_are_ignored() {
        var threeLines = toInputStream("one\none two\tone two three");
        var top3Words = underTest.top_3_words(threeLines);
        assertThat(top3Words, is(arrayContaining("one", "two", "three")));
    }

    @Test
    public void two_unique_words_only() {
        var twoUniqueWords = toInputStream("    //wont won't won't");
        var top3Words = underTest.top_3_words(twoUniqueWords);
        assertThat(top3Words, is(arrayContaining("won't", "wont")));
    }

    @Test
    public void don_quixote_excerpt_returns_top_3_words() {
        var narrative = toInputStream("In a village of La Mancha, the name of which I have no desire to " +
                "call to mind, there lived not long since one of those gentlemen that keep a lance in the lance-rack, " +
                "an old buckler, a lean hack, and a greyhound for coursing. " +
                "An olla of rather more beef than mutton, a salad on most nights, scraps on Saturdays, lentils on " +
                "Fridays, and a pigeon or so extra on Sundays, made away with three-quarters of his income.");
        var top3Words = underTest.top_3_words(narrative);
        assertThat(top3Words, is(arrayContaining("a", "of", "on")));
    }

    private InputStream toInputStream(String s) {
        return new ByteArrayInputStream(s.getBytes());
    }

}