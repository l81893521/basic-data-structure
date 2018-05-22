package will.zhang.trie;

import java.util.TreeMap;

/**
 * Trie
 * Trie是一种专门设计用来处理字符串的数据结构
 */
public class BTrie {

    //节点
    private Node root;
    //Trie中单词的个数
    private int size;

    public BTrie(){
        root = new Node();
        size = 0;
    }

    /**
     * 获得Trie中存在多少个单词
     * @return
     */
    public int getSize(){
        return size;
    }

    private class Node {
        //访问到当前Node的时候是否已经找到了一个单词
        public boolean isWord;
        //每一个节点的next都是TreeMap
        private TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    /**
     * 向Trie中添加一个单词
     * @param word
     */
    public void add(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        if(!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 查询单词是否存在Trie中
     * @param word
     * @return
     */
    public boolean contains(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }


}
