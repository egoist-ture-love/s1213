package ltd.egoist;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2021/5/11 20:43
 * @Created by Lenovo
 */
public class Main {
    public static void main(String[] args) {
        test01();
    }
    public static void test01(){
        Trie<Integer> trie = new Trie<>();
        trie.add("cat", 1);
        trie.add("dog", 2);
        trie.add("catalog", 3);
        trie.add("cast", 4);
        trie.add("小码哥", 5);
        Asserts.test(trie.size() == 5);
        Asserts.test(trie.startsWith("do"));
        Asserts.test(trie.startsWith("c"));
        Asserts.test(trie.startsWith("ca"));
        Asserts.test(trie.startsWith("cat"));
        Asserts.test(trie.startsWith("cata"));
        Asserts.test(!trie.startsWith("hehe"));
        Asserts.test(trie.get("小码哥") == 5);
        Asserts.test(trie.remove("cat") == 1);
        Asserts.test(trie.remove("catalog") == 3);
        Asserts.test(trie.remove("cast") == 4);
        Asserts.test(trie.size() == 2);
        Asserts.test(trie.startsWith("小"));
        Asserts.test(trie.startsWith("do"));
        Asserts.test(!trie.startsWith("c"));
    }
}
