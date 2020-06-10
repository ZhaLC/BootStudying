package com.zlc.algorithm.leetcode;

/**
 * @author : ZLC
 * @create : 2020-06-10 15:17
 * @desc : 回文数
 **/
public class Q9_IsPalindrome {
    public boolean isPalindrome(int x) {

        /**
         * 4、取出后半段数字进行取反(相对于整个取反: 次数少, 整个取反不是回文数的话可能会超过int范围  溢出)
         *  取反一半注意的问题: 位数是偶数, 取反后一样位数; 位数是奇数, 后半部分取反后比前半部分多一位
         */
        if(x < 0 || (x % 10 == 0 && x != 0)){
            return false;
        }
        int revertedNumber = 0;
        //判断一半 防止不是回文数时溢出  减少循环次数
        while(x > revertedNumber){
            revertedNumber = revertedNumber * 10 + (x % 10);
            x /= 10;
        }
        // || 后面用于判断x是奇数位的情况
        return x == revertedNumber || x == (revertedNumber / 10);


        /**
         * 3、数学解法
         */
//        if(x < 0){
//            return false;
//        }
//        int div = 1;
//        while(x / div >= 10){
//            div *= 10;
//        }
//        while(x > 0){
//            int high = x / div;
//            int low = x % 10;
//            if(high != low){
//                return false;
//            }
//            x = (x % div) / 10;
//            //下面这就不行? 是不行, 需要先去掉高位 要不热div没法用...
//            //x = (x / 10) % div;
//            div /= 100;
//        }
//        return true;

        /**
         * 2、使用自带函数 StringBuilder的reverse
         */
//        String xStrRe = new StringBuilder(x + "").reverse().toString();
//        return xStrRe.equals(x + "");

        /**
         * 1、纯手工...
         */
//        String str = String.valueOf(x);
//        char[] arr = str.toCharArray();
//        int len = arr.length;
//        if(len == 0){
//            return true;
//        }
//        for (int i = 0; i < len/2; i++) {
//            if(arr[i] != arr[len-1-i]){
//                return false;
//            }
//        }
//        return true;
    }

    public static void main(String[] args) {
        StringBuffer aa = new StringBuffer("adfg");
        aa.reverse();
        System.out.println(aa);
    }
}
