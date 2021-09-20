
public class Dictionary {

    // initialization
    int ALPHABET_SIZE = 26;
    TrieNode root = new TrieNode();

    // Insert function
    public void insert(String key) {
        // initialization
        int level;
        int length = key.length();
        int index;

        TrieNode current = this.root;

        // insert characters
        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (current.getChild(index) == null) {
                current.setChild(index);
            }
            current = current.getChild(index);
        }

        // leaf indicator
        current.setLeaf(true);
    }

    // Search function
    public boolean search(String key) {
        // initialization
        int level;
        int length = key.length();
        int index;

        TrieNode current = root;

        // Search characters one by one
        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';

            if (current.getChild(index) == null) {
                return false;
            }

            current = current.getChild(index);
        }

        return (current != null && current.isLeaf);

    }

    // Display function: display all the elements in the trie
    public void display(TrieNode root, char str[], int level) {
        int i;

        if (root.isLeaf) {
            System.out.println("");
        }
        for (i = 0; i < ALPHABET_SIZE; i++) {
            if (root.getChild(i) != null) {
                str[level] = (char) (i + 'a');
                System.out.print(str[level]);
                display(root.getChild(i), str, level + 1);
            }
        }
    }
}
