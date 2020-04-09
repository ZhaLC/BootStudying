package com.zlc.algorithm.sort;

public class BubbleSortTest {

    /**
     * 最简单最原始
     * @param arr
     * @return
     */
    public static int[] sort1(int[] arr){
        for(int i = 0; i < arr.length-1; i++){
            for(int j = 0; j < arr.length-i-1; j++ ){
                int temp = 0;
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.format("第 %d 趟排序: \t", i);
            print(arr);
        }
        return arr;
    }

    /**
     * 添加有序标志位
     * @param arr
     * @return
     */
    public static int[] sort2(int[] arr){

        for(int i = 0; i < arr.length-1; i++){
            boolean isSorted = true;
            for(int j = 0; j < arr.length-i-1; j++ ){
                int temp = 0;
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    isSorted = false;
                }
            }
            if(isSorted){
                break;
            }
            System.out.format("第 %d 趟排序: \t", i);
            print(arr);
        }
        return arr;
    }

    /**
     * 添加有序区界定(记录上一次交换的位置, 下次比到这里就可以了)
     * 有序区: 有顺序的区间,数组的后面 第一次1, 第二次2, 第三次3...
     * @param arr
     * @return
     */
    public static int[] sort3(int[] arr){
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        //无序部分数组的边界 每次比较比较到这里就可以
        int sortBorder = arr.length - 1;
        for(int i = 0; i < arr.length-1; i++){
            boolean change = false;
            for(int j = 0; j < sortBorder; j++ ){
                int temp = 0;
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    lastExchangeIndex = j;
                    change = true;
                }
            }
            sortBorder = lastExchangeIndex;
            if(!change){
                break;
            }
            System.out.format("第 %d 趟排序: \t", i);
            print(arr);
        }
        return arr;
    }

    /**
     * 鸡尾酒排序(双向)
     * @param arr
     * @return
     */
    public static int[] sort4(int[] arr){

        for(int i = 0; i < arr.length-1; i++){
            boolean change = false;
            for(int j = 0; j < arr.length-i-1; j++ ){
                int temp = 0;
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    change = true;
                }
            }
            if(!change){
                break;
            }
            System.out.format("第 %d 趟排序: \t", i);
            print(arr);
        }
        return arr;
    }


    /**
     *
     * @param arr
     */
    public static void print(int[] arr){
        for(int i : arr){
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2,4,9,6,5,8,7,3};
        int[] arr2 = {5,8,6,3,9,2,1,7};
        int[] arr3 = {3,4,2,1,5,6,7,8};
        sort1(arr1);
        System.out.println("=============");
        sort2(arr2);
        System.out.println("=============");
        sort3(arr3);


    }

}
