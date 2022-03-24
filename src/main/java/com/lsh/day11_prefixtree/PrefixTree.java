package com.lsh.day11_prefixtree;

import java.util.HashMap;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/23 8:22 下午
 * @desc ：前缀树（prefix tree  )/ （trie）
 * 1）单个字符串中，字符从前到后的加到一棵多叉树上
 * 2）字符放在路上，节点上有专属的数据项（常见的是pass和end值）
 * 3）所有样本都这样添加，如果没有路就新建，如有路就复用
 * 4）沿途节点的pass值增加1，每个字符串结束时来到的节点end值增加1
 *
 * Node1 使用数组实现 （数组长度为26表示26个字母）
 * Node2 使用HashMap实现
 *
 */
public class PrefixTree {

    //前缀数节点类型
    public static class Node1{
        public int pass;
        public int end;
        public Node1[] nexts;

        public Node1(){
            this.pass = 0;
            this.end = 0;
            // 0 a
            // 1 b
            // 2 c
            // 3 d
            // ...
            // Node[paht] == null 说明不存在节点
            // Node[paht] != null 说明存在节点

            nexts = new Node1[26];
        }
    }

    //此方式使用HashMap数据结构实现
    public static class Node2 {
        public int pass;
        public int end;
        public HashMap<Integer, Node2> nexts;

        public Node2() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class Trie{
        private Node1 root;
        public Trie(){
            this.root = new Node1();
        }

        public void insert(String word){
            if (word == null) return;
            char[] str = word.toCharArray();
            Node1 node = root;
            node.pass++;
            int path = 0;
            for (int i = 0; i < str.length; i++) {
                //用当前字符的ascII码减去 a 的ascII码
                path = str[i] - 'a';
                if (node.nexts[path]  == null){
                    node.nexts[path] = new Node1();
                }
                //节点继续向下移动
                node = node.nexts[path];
                node.pass++;

            }
            //node到达最后节点，此时end+1
            node.end++;

        }

        /**
         * 删除单词
         * @param word
         */
        public void delete(String word){
            //先通过search方法查询是否含有该单词
            if (search(word) != 0){
                //
                Node1 node = root;
                char[] chars = word.toCharArray();
                node.pass --;
                int index = 0;
                for (int i = 0; i < chars.length; i++) {
                    index = chars[i] - 'a';
                    if (--node.nexts[index].pass == 0){
                        //如果下一个节点的pass减一后等于0 ，则将之后节点删除
                        node.nexts[index] = null;
                        return;
                    }
                    //-1后的node节点的pass不为0 ，则节点向下移动
                    node = node.nexts[index];
                }
                //注意：此处end--的情况是，没有置空节点的情况，即删除了一个路径重复的节点，一直删除到了最后，所以最后节点的end需要减一
                node.end --;
            }

        }

        /**
         * 查询以这个前缀开头的单词有几个
         * @param pre
         * @return
         */
        public int prefinxNum(String pre){
            if (pre == null) return 0;
            Node1 node = root;
            int index = 0;
            char[] chars = pre.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) return 0;
                node = node.nexts[index];
            }
            return node.pass;
        }


        /**
         * 查询word这个单词出现了几次
         * @param word
         * @return
         */
        public int search(String word){
            if (word == null) return 0;
            char[] chars = word.toCharArray();
            Node1 node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null){
                    //说明这么节点下面没有路了,即表示这个字符没有出现过
                    return 0;
                }
                //当前字符出现过，节点向下移动
                node = node.nexts[index];
            }
            return node.end;
        }
    }

    public static class Trie2 {
        private Node2 root;

        public Trie2() {
            root = new Node2();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chs = word.toCharArray();
            Node2 node = root;
            node.pass++;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    node.nexts.put(index, new Node2());
                }
                node = node.nexts.get(index);
                node.pass++;
            }
            node.end++;
        }


        public void delete(String word) {
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                Node2 node = root;
                node.pass--;
                int index = 0;
                for (int i = 0; i < chs.length; i++) {
                    index = (int) chs[i];
                    if (--node.nexts.get(index).pass == 0) {
                        node.nexts.remove(index);
                        return;
                    }
                    node = node.nexts.get(index);
                }
                node.end--;
            }
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.end;
        }
        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.pass;
        }
    }

}
