
public class Test {

    public static void main(String[] args) {
        // Set input words
        String words[] = {"iron", "man", "captain", "dog"};

        // initialize dictionary trie
        Dictionary dt = new Dictionary();
        for (int i = 0; i < words.length; i++) {
            dt.insert(words[i]);
        }

        // Display elements
        int level = 0;
        char[] str = new char[20];
        System.out.println("The dictionary contains: ");
        dt.display(dt.root, str, level);

        // search
        // Set search words
        System.out.println("Search result: ");
        String search_words[] = {"captain", "hulk"};
        String messages[] = {"Not Found!", "Found!"};
        String word;
        for (int i = 0; i < search_words.length; i++) {
            word = search_words[i];
            if (dt.search(word) == true) {
                System.out.println(word + " --- " + messages[1]);
            } else {
                System.out.println(word + " --- " + messages[0]);
            }
        }

    }
}
