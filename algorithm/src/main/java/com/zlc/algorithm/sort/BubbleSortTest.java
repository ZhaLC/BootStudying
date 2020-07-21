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
     * 鸡尾酒排序(双向, 适用于大部分都是有序的情况, 也是O(n²))
     * 不添加有序区 也可以添加有序区逻辑
     * https://mp.weixin.qq.com/s/CoVZrvis6BnxBQgQrdc5kA
     * @param arr
     * @return
     */
    public static int[] sort4(int[] arr){
        int temp = 0;
        for(int i = 0; i < arr.length/2; i++){
            boolean isSorted = true;
            //奇数轮 从左到右比较和交换
            for(int j = i; j < arr.length-1-i; j++ ){
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
            //偶数轮之前 从新标记为true
            isSorted = true;
            //偶数轮 从右向左比较和交换
            for(int j = arr.length-1-i; j > i; j--){
                if(arr[j] < arr[j-1]){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
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
     * 鸡尾酒排序(添加了有序区 O(n²))
     * @param arr
     * @return
     */
    public static int[] sort5(int[] arr){
        int temp = 0;
        int lastRightExchangeIndex = 0;
        int lastLeftExchangeIndex = 0;
        int leftSortBorder = 0;
        int rightSortBorder = arr.length-1;
        for(int i = 0; i < arr.length/2; i++){
            boolean isSorted = true;
            for(int j = leftSortBorder; j < rightSortBorder; j++){
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    isSorted = false;
                    lastRightExchangeIndex = j;
                }
            }
            rightSortBorder = lastRightExchangeIndex;
            if(isSorted){
                break;
            }

            isSorted = true;
            for(int j = rightSortBorder; j > leftSortBorder; j--){
                if(arr[j] < arr[j-1]){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    isSorted = false;
                    lastLeftExchangeIndex = j;
                }
            }
            leftSortBorder = lastLeftExchangeIndex;
            if(isSorted){
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
        int[] arr4 = {2,3,4,5,6,7,8,1};
        int[] arr5 = {2,3,4,5,6,7,8,1};
        System.out.println("=====1========");
        sort1(arr1);
        System.out.println("=====2========");
        sort2(arr2);
        System.out.println("=====3========");
        sort3(arr3);
        System.out.println("=====4========");
        sort4(arr4);
        System.out.println("=====5========");
        sort5(arr5);
    }

    static class BubbleReview{
        /*基础写法*/
        public static void bubbleSort(int[] arr){
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length-i-1; j++) {
                    if(arr[j] > arr[j+1]){
//                        int temp = arr[j];
//                        arr[j] = arr[j+1];
//                        arr[j+1] = temp;
                        /**
                         * 一个数按位异或自己等于0 a^a=0;
                         * 一个数按位异或0等于自己 a^0=a;
                         */
                        arr[j] = arr[j] ^ arr[j+1];
                        arr[j+1] = arr[j] ^ arr[j+1];
                        arr[j] = arr[j] ^ arr[j+1];
                    }
                }
            }
            print(arr);
        }
        /*加入有序标识*/
        public static void bubbleSort2(int[] arr){
            for (int i = 0; i < arr.length; i++) {
                boolean isOrdered = true;
                for (int j = 0; j < arr.length-i-1; j++) {
                    if(arr[j] > arr[j+1]){
//                        int temp = arr[j];
//                        arr[j] = arr[j+1];
//                        arr[j+1] = temp;
                        /**
                         * 一个数按位异或自己等于0 a^a=0;
                         * 一个数按位异或0等于自己 a^0=a;
                         */
                        arr[j] = arr[j] ^ arr[j+1];
                        arr[j+1] = arr[j] ^ arr[j+1];
                        arr[j] = arr[j] ^ arr[j+1];
                        isOrdered = false;
                    }
                }
                if(isOrdered){
                    break;
                }

            }
            print(arr);
        }
        /*加入有序区界定*/
        public static void bubbleSort3(int[] arr){
            int lastExchange = 0;
            int sortBorder = arr.length-1;
            for (int i = 0; i < arr.length; i++) {
                boolean isOrdered = true;
                for (int j = 0; j < sortBorder; j++) {
                    if(arr[j] > arr[j+1]){
//                        int temp = arr[j];
//                        arr[j] = arr[j+1];
//                        arr[j+1] = temp;
                        /**
                         * 一个数按位异或自己等于0 a^a=0;
                         * 一个数按位异或0等于自己 a^0=a;
                         */
                        arr[j] = arr[j] ^ arr[j+1];
                        arr[j+1] = arr[j] ^ arr[j+1];
                        arr[j] = arr[j] ^ arr[j+1];
                        isOrdered = false;
                        lastExchange = j;
                    }
                }
                sortBorder = lastExchange;
                if(isOrdered){
                    break;
                }
            }
            print(arr);
        }




        public static void main(String[] args) {
            int[] arr1 = {1,2,4,9,6,5,8,7,3};
            bubbleSort3(arr1);
        }


    }

}
