package com.zlc.algorithm.sort;

import java.util.Arrays;

/**
 * @desc :
 **/
public class HeapSort {

    /**
     * 最大堆 下沉操作
     * @param arr 待调整的堆
     * @param parentIndex 要下沉的节点
     * @param length 堆的有效大小
     */
    public static void downAdjust(int[] arr, int parentIndex, int length){
        int temp = arr[parentIndex];
        int childIndex = parentIndex * 2 + 1;
        while(childIndex < length){
            // 如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子
            if(childIndex + 1 < length && arr[childIndex+1] > arr[childIndex]){
                childIndex++;
            }
            if(temp > arr[childIndex]){
                break;
            }
            arr[parentIndex] = arr[childIndex];
            parentIndex = childIndex;
            childIndex = parentIndex * 2 + 1;
        }
        arr[parentIndex] = temp;
    }

    public static void heapSort(int[] arr){
        // 下沉操作构建二叉堆
        for(int i = (arr.length - 2) / 2; i >= 0; i--){
            downAdjust(arr, i, arr.length);
        }
        System.out.println(Arrays.toString(arr));
        // 循环删除堆顶元素, 移到集合尾部, 调节堆产生新的堆顶
        for (int i = arr.length - 1; i > 0; i--) {
            // 最后一个元素和第一个元素进行互换
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            // 下沉调整最大堆
            downAdjust(arr, 0 , i);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
