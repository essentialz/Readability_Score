package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Document {
    private final String content;
    private final List<String> wordList;
    private final long sentences;
    private final long words;
    private final long characters;
    private final long syllables;
    private final long polysyllables;

    public Document(String path) throws IOException {
            this.content = Files.readString(Paths.get(path));
            this.wordList = makeWordList();
            this.sentences = countSentences();
            this.words = countWords();
            this.characters = countCharacters();
            this.syllables = countSyllables();
            this.polysyllables = countPolysyllables();
    }

    private List<String> makeWordList () {
        String[] words = content.replaceAll("[^\\w\\s]", "").split(" +");

        return Arrays.asList(words);
    }

    private long countSentences() {
        return content.split("[.!?]").length;
    }

    private long countWords() {
        return wordList.size();
    }

    private long countCharacters() {
        return content.replaceAll("\\s", "").length();
    }

    private long countSyllables() {
        return wordList.stream()
                .mapToLong(Document::countSyllablesInWord)
                .sum();
    }

    private long countPolysyllables() {
        return wordList.stream()
                .mapToLong(Document::countSyllablesInWord)
                .filter(s -> s > 2)
                .count();
    }

    private static int countSyllablesInWord(String word) {
        return Math.max(1, word.toLowerCase()
                .replaceAll("e$", "")
                .replaceAll("[aeiouy]{2}", "a")
                .replaceAll("[^aeiouy]", "")
                .length());
    }

    protected long getSentences() {
        return sentences;
    }

    protected long getWords() {
        return words;
    }

    protected long getCharacters() {
        return characters;
    }

    protected long getSyllables() {
        return syllables;
    }

    protected long getPolysyllables() {
        return polysyllables;
    }

    public String toString() {
        return String.format("Words: %s" +
                "%nSentences: %s" +
                "%nCharacters: %s" +
                "%nSyllables: %s" +
                "%nPolysyllables: %s",
                words, sentences, characters, syllables, polysyllables);
    }
}
