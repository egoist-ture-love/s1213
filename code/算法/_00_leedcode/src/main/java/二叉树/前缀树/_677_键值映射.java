package 二叉树.前缀树;

public class _677_键值映射 {
    class TrieNode{
        int num;
        TrieNode[] trieNodes = new TrieNode[26];
    }
    TrieNode trie;
    public _677_键值映射() {
        trie = new TrieNode();
    }

    public void insert(String key, int val) {
        TrieNode p = trie;
        for(int i = 0; i < key.length();i++){
            int num = key.charAt(i) - 'a';
            if (p.trieNodes[num] == null) {
                p.trieNodes[num] = new TrieNode();
            }
            p =  p.trieNodes[num];
        }
        p.num = val;
    }

    public int sum(String prefix) {
        TrieNode p = trie;
        for(int i = 0; i < prefix.length();i++){
            int num = prefix.charAt(i) - 'a';
            if (p.trieNodes[num] == null) return 0;
            p =  p.trieNodes[num];
        }
        return dfs(p);
    }
    private int dfs(TrieNode p){
        int val = p.num;
        for(TrieNode i : p.trieNodes){
            if(i != null){
                val = val + dfs(i);
            }
        }
        return val;
    }
}
