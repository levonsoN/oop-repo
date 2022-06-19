public class Replacer {
    public String replace(String input, int k, char c) {
        String[] words = input.replaceAll("[^a-zA-Z ]", "").split("\\s+");
        String[] resultWords = new String[words.length];
        for (int i = 0; i < words.length; i++)
            if (words[i].length() > k)
                resultWords[i] = words[i].replaceAll(String.format("%c", c), "");
        String result = input;
        for (int i = 0; i < resultWords.length; i++)
            if (resultWords[i] != null)
                result = result.replaceAll(words[i], resultWords[i]);
        return result;
    }
}
