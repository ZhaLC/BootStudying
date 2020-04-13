package com.zlc.algorithm.sort;

import java.util.Arrays;

/**
 * @author : ZLC
 * @create : 2020-04-10 15:06
 * @desc : 快排O(nlogn) 递归实现(双向、单向)、栈实现、
 **/
public class QuickSort {

    //挖坑法 基准元素位置相当于一个坑 右侧的小于基准元素就右侧这个位置为新坑;左侧的大于基准元素, 左侧这个则为新坑(用新坑处的值填旧坑处的值)
    //递归实现 双向
    public static void quickSort1(int[] arr, int startIndex, int endIndex){
        if(startIndex >= endIndex){
            return;
        }
        int pivotIndex = partition1(arr, startIndex, endIndex);
        quickSort1(arr, startIndex, pivotIndex-1);
        quickSort1(arr,pivotIndex+1, endIndex);
    }
    public static int partition1(int[] arr, int startIndex, int endIndex){
        //取第一个元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        //坑的位置 初始等于pivot的位置
        int index = startIndex;

        while(right >= left){
            //right指针从右向左移动
            while(right >= left){
                if(arr[right] < pivot){
                    arr[left] = arr[right];
                    index = right;
                    left++;
                    break;
                }
                right--;
            }
            //left指针从左向右移动
            while(right >= left){
                if(arr[left] > pivot){
                    arr[right] = arr[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }
        }
        arr[index] = pivot;
        return index;
    }


    public static void main(String[] args) {
        int[] arr = {4,7,6,5,3,2,8,1};
        quickSort1(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

}
