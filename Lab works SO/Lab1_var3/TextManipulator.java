public class TextManipulator {
    public String removeSpaces(String text) {
        return text.replaceAll("\\s+", "");
    }

    public String removeNewLines(String text) {
        return text.replaceAll("\\r?\\n", "");
    }

    public String mirrorText(String text) {
        return new StringBuilder(text).reverse().toString();
    }

    public String changeWord(String text, String oldWord, String newWord) {
        return text.replace(oldWord, newWord);
    }
}