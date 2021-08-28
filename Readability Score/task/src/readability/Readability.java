package readability;

import com.sun.jdi.request.InvalidRequestStateException;

public class Readability {
    protected static String getScore(Document doc, Request request) {
        switch (request) {
            case ARI:
                double ari = getARI(doc);
                return formatScore(Request.ARI.getName(), ari , getAgeRange(ari));
            case FK:
                double fk = getFK(doc);
                return formatScore(Request.FK.getName(), fk , getAgeRange(fk));
            case SMOG:
                double smog = getSmog(doc);
                return formatScore(Request.SMOG.getName(), smog , getAgeRange(smog));
            case CL:
                double cl = getCl(doc);
                return formatScore(Request.CL.getName(), cl , getAgeRange(cl));
            case ALL:
                double avg = (getAgeRange(getARI(doc)) + getAgeRange(getFK(doc)) + getAgeRange(getSmog(doc)) + getAgeRange(getCl(doc))) / 4.0;

                return String.format("%s%s%s%s%n%n" +
                                "This text should be understood in average by %.2f-year-olds.",
                        getScore(doc, Request.ARI),
                        getScore(doc, Request.FK),
                        getScore(doc, Request.SMOG),
                        getScore(doc, Request.CL),
                        avg);
            default:
                throw new InvalidRequestStateException("Invalid Request");
        }
    }

    private static String formatScore(String name, double score, int age) {
        return String.format("%n%s: %.2f (about %d-year-olds).", name, score, age);
    }

    private static double getARI(Document doc) {
        final double charsPerWord = ((double) doc.getCharacters() / doc.getWords());
        final double wordsPerSentence = ((double) doc.getWords() / doc.getSentences());

        return 4.71 * charsPerWord + 0.5 * wordsPerSentence - 21.43;
    }

    private static double getFK(Document doc) {
        final double wordsPerSentence = ((double) doc.getWords() / doc.getSentences());
        final double syllablesPerWord = ((double) doc.getSyllables() / doc.getWords());

        return 0.39 * wordsPerSentence + 11.8 * syllablesPerWord - 15.59;
    }

    private static double getSmog(Document doc) {
        return 1.043 * Math.sqrt(doc.getPolysyllables() * 30.0 / doc.getSentences()) + 3.1291;
    }

    private static double getCl(Document doc) {
        final double charsPerWord = ((double) doc.getCharacters() / doc.getWords());
        final double sentencesPerWord = ((double) doc.getSentences() / doc.getWords());

        return 0.0588 * charsPerWord * 100 - 0.296 * sentencesPerWord * 100 - 15.8;
    }

    private static int getAgeRange(double score) {
        int[] range = {6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 24, 25};

        return range[(int) Math.round(score) - 1];
    }
}
