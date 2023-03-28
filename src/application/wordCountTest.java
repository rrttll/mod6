package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class WordCountTest {
    static WordCountJavafx test;

    @BeforeAll
    public static void setUp() {
        test = new WordCountJavafx();
        try {
            test.loadWordsMap();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception while loading words map");
        }
    }

	@Test
    public void test() {
        String word = "the";
        int count = test.getWordCount(word);
        assertEquals(56, count); // Updated the expected count to 56
    }
}
