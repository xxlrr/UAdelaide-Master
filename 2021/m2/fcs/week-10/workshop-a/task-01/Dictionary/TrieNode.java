
public class TrieNode {

    // initialization
    // isLeaf is true if the node is a leaf, i.e. end of a word
    protected boolean isLeaf;

    // initialize 26 children
    int ALPHABET_SIZE = 26;
    protected TrieNode[] children = new TrieNode[ALPHABET_SIZE];

    // Constructor
    TrieNode() {
        this.isLeaf = false;
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            this.children[i] = null;
        }
    }

    // assessors
    public boolean isLeaf() {
        return this.isLeaf;
    }

    public TrieNode getChild(int index) {
        return this.children[index];
    }

    // mutators
    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public void setChild(int index) {
        this.children[index] = new TrieNode();
    }

}
