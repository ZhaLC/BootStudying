package com.zlc.algorithm.sort;

import java.util.Arrays;

/**
 * @desc :
 **/
public class Heap {

    /**
     * 最小堆 上浮调整
     * @param arr 待调整的堆
     */
    public static void upAdjust(int[] arr) {
        int childIndex = arr.length - 1;
        int parentIndex = (childIndex - 1) / 2;
        // 用于最后的赋值
        int temp = arr[childIndex];
        while (childIndex > 0 && temp < arr[parentIndex]) {
            // 单向赋值即可
            arr[childIndex] = arr[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }
        arr[childIndex] = temp;
    }

    /**
     * 最小堆 下沉操作
     * @param arr         待调整的堆
     * @param parentIndex 要下沉的父节点
     * @param length      堆的有效大小
     */
    public static void downAdjust(int[] arr, int parentIndex, int length) {
        // 用于最后的赋值
        int temp = arr[parentIndex];
        int childIndex = parentIndex * 2 + 1;
        while (childIndex < length) {
            // 如果有右孩子且右孩子小于左孩子 则直接定位到右孩子
            if (childIndex + 1 < length && arr[childIndex + 1] < arr[childIndex]) {
                childIndex++;
            }
            if (temp <= arr[childIndex]) {
                break;
            }
            arr[parentIndex] = arr[childIndex];
            parentIndex = childIndex;
            childIndex = parentIndex * 2 + 1;
        }
        arr[parentIndex] = temp;
    }

    /**
     * 构建最小堆
     *
     * @param arr 待调整的堆
     */
    public static void buildHeap(int[] arr) {
        // 从最后一个非叶子节点开始，依次下沉调整(这里原来小灰公众号那里写的不对)
        for (int i = (arr.length-2) / 2; i >= 0; i--) {
            downAdjust(arr, i, arr.length);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        upAdjust(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
        buildHeap(arr);
        System.out.println(Arrays.toString(arr));
    }

}
