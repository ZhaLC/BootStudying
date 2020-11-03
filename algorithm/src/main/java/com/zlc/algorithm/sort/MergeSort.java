package com.zlc.algorithm.sort;

import java.util.Arrays;

/**
 * @desc : 归并排序
 **/
public class MergeSort {
    public static void mergeSort(int[] arr, int low, int high){
        // 递归结束条件 子序列有序 相等说明子序列只有一个元素肯定有序
        if(low < high){
            int mid = (low + high)/2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid+1, high);
            merge(arr, low , mid, high);
        }
    }

    public static void merge(int[] arr, int low, int mid, int high){
        //开辟临时数组
        int[] temp = new int[high-low+1];
        //设置指针
        int i = low, j = mid+1, t = 0;
        while(i <= mid && j <= high){
            if(arr[i] <= arr[j]){
                temp[t++] = arr[i++];
            }else{
                temp[t++] = arr[j++];
            }
        }
        //左侧有剩余的话就放过去
        while (i <= mid){
            temp[t++] = arr[i++];
        }
        //右侧有剩余的话放过去
        while (j <= high){
            temp[t++] = arr[j++];
        }
        //把临时集合的元素复制到原数组
        for (int k = 0; k < temp.length; k++) {
            arr[k+low] = temp[k];
        }
    }

    public static void main(String[] args) {
        int[] arr = {4,7,6,5,3,2,8,1};
        mergeSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
