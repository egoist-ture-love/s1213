package 二叉树.前缀树;

public class _208_实现Trie前缀树 {
    /** Initialize your data structure here. */
    class TrieNode{
        boolean end;
        TrieNode[] trieNode = new TrieNode[26];
    }
    TrieNode trie;
    public _208_实现Trie前缀树() {
        trie = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode p = trie;
        for(int i = 0; i < word.length(); i++){
            int num = word.charAt(i) - 'a';
            if(p.trieNode[num] == null) p.trieNode[num] = new TrieNode();
            p = p.trieNode[num];
        }
        p.end = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode p = trie;
        for(int i = 0; i < word.length(); i++){
            int num = word.charAt(i) - 'a';
            if(p.trieNode[num] == null) return false;
            p = p.trieNode[num];
        }
        return p.end;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode p = trie;
        for(int i = 0; i < prefix.length(); i++){
            int num = prefix.charAt(i) - 'a';
            if(p.trieNode[num] == null) return false;
            p = p.trieNode[num];
        }
        return true;
    }
}
