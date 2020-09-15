package com.zlc.algorithm.sword;

/**
 * @desc :
 **/
public class O_67_StrToInt {
    public int strToInt(String str) {
        char[] chars = str.trim().toCharArray();
        int res = 0;
        int sign = 1; // 表示 符号位，直接与结果相乘 即可
        int index = 1; // 遍历 chars数组 的 下标
        int border = Integer.MAX_VALUE / 10; // 用于判断 当前数字 是否 到达最大值 的1/10，以便后续处理
        if(chars.length <= 0){
            return 0;
        }
        if(chars[0] == '-'){
            sign = -1;
        }else if(chars[0] != '+'){
            index = 0;
        }
        for( ;index < chars.length; index++){
            char currChar = chars[index];
            if(currChar < '0' || currChar > '9'){
                break;
            }
            //这里的7是根据Integer的最大值最后一位是7写的 2的31次方-1: 2147483647  最小-2147483648
            if(res > border || (res == border && currChar > '7')){
                return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            //这里char转数字
            res = res * 10 + currChar - '0';
        }
        return res * sign;
    }
}
