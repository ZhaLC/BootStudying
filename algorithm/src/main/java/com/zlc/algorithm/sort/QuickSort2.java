package com.zlc.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSort2  {
    public static void main(String[] args) {
//        List<String> list=new ArrayList<>(10);
//        System.out.println(list.size());
        int[] arr = {4,7,6,5,3,2,8,1};
        int[] a = quickSort(arr, 0, arr.length -1);
        printArr(a);
    }

    public static int[] sort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        return quickSort(arr, 0, arr.length - 1);
    }

    private static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            printArr(arr);
            int partitionIndex = partition(arr, left, right);
            System.out.println("partitionIndex:"+partitionIndex);
            printArr(arr);
            quickSort(arr, left, partitionIndex - 1);
            printArr(arr);
            quickSort(arr, partitionIndex + 1, right);
            printArr(arr);
        }
        return arr;
    }

    private static int partition(int[] arr, int left, int right) {
        // 设定基准值（pivot）
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArr(int [] arr){
        for (int i : arr) {
            System.out.print(i+",");
        }
        System.out.println();
    }
}