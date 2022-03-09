package com.lsh.leetcode;

import java.util.HashMap;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/9 9:34 下午
 * @desc ：13. Roman to Integer
 * https://leetcode.com/problems/roman-to-integer/
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 给你一个罗马数字，将它转化为阿拉伯数字
 * Input: s = "III"
 * Output: 3
 * Explanation: III = 3.
 *
 * Input: s = "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 *
 * Input: s = "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 *  I  V  X   L    C    D     M
 *  1  5  10  50  100  500  1000
 * 注意：
 * IV = 4   IX = 9  XL = 40   XC= 90   CD = 400  CM = 900
 *
 */
public class Code13_Roman2Integer {
    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }
    public static int romanToInt(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        HashMap<Character, Integer> hashMap = new HashMap<>();
        hashMap.put('I',1);
        hashMap.put('V',5);
        hashMap.put('X',10);
        hashMap.put('L',50);
        hashMap.put('C',100);
        hashMap.put('D',500);
        hashMap.put('M',1000);

        int index = s.length()-1;
        int ans = hashMap.get(s.charAt(index--));
        while (index >= 0){
            //从左往右 由大变小
            //如果左边小于右边 则做减法    MCMXCIV
            if (hashMap.get(s.charAt(index)) < hashMap.get(s.charAt(index+1))){
                ans -= hashMap.get(s.charAt(index--));
            }else {
                ans += hashMap.get(s.charAt(index--));
            }
        }
        return ans;
    }
}
