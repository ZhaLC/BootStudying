package com.zlc.algorithm.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author : ZLC
 * @create : 2020-04-10 15:06
 * @desc : 快排O(nlogn) 最坏O(n²) 递归实现(双向、单向)、栈实现、 不稳定排序
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
    //挖坑法 基准元素位置相当于一个坑 右侧的小于基准元素就右侧这个位置为新坑;左侧的大于基准元素, 左侧这个则为新坑(用新坑处的值填旧坑处的值)
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

    //指针交换法 双边 递归方法和挖坑是一样的
    //递归实现 双向
    public static void quickSort2(int[] arr, int startIndex, int endIndex){
        if(startIndex >= endIndex){
            return;
        }
        int pivotIndex = partition2(arr, startIndex, endIndex);
        quickSort1(arr, startIndex, pivotIndex-1);
        quickSort1(arr,pivotIndex+1, endIndex);
    }
    //指针交换法 双边遍历, 右边找小于pivot的停下, 左边找到大于pivot的停下, 互换位置, 最后将pivot和指针重合处替换
    public static int partition2(int[] arr, int startIndex, int endIndex){
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        while(right != left){
            // 注意: 因为上面pivot选择的startIndex处的元素, 所以下面顺序必须是先处理right指针, 再处理left指针;
            // 理解是先出来left指针可能会多换出去一步, 最后pivot和重合点交换的时候不对了
            // pivot如果选择endIndex处的元素, 则先出来left, 后处理right可以
            while(right > left && arr[right] >= pivot){
                right--;
            }
            while(right > left && arr[left] <= pivot){
                left++;
            }
//            while(right > left && arr[right] >= pivot){
//                right--;
//            }
            if(left < right){
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        //pivot指针和重合点交换 左边都小于pivot  右边都大于pivot 此轮结束
        int p = arr[left];
        arr[left] = arr[startIndex];
        arr[startIndex] = p;
        return left;
    }

    //指针交换法 单边 递归方法和挖坑、双边是一样的
    //递归实现 单边
    public static void quickSort3(int[] arr, int startIndex, int endIndex){
        if(startIndex >= endIndex){
            return;
        }
        int pivotIndex = partition3(arr, startIndex, endIndex);
        quickSort3(arr, startIndex, pivotIndex-1);
        quickSort3(arr,pivotIndex+1, endIndex);
    }
    //指针交换法 单边遍历
    public static int partition3(int[] arr, int startIndex, int endIndex){
        int pivot = arr[startIndex];
        int mark = startIndex;
        for(int i = startIndex+1; i <= endIndex; i++){
            if(arr[i] < pivot){
                mark++;
                int temp = arr[mark];
                arr[mark] = arr[i];
                arr[i] = temp;
            }
        }
        //注意这里啊
        int temp = arr[mark];
        arr[mark] = pivot;
        arr[startIndex] = temp;
        return mark;
    }

    //快排 栈实现 使用的双边、指针交换法
    public static void quickSortWithStack(int[] arr, int startIndex, int endIndex) {
        //用一个集合栈来代替递归的函数栈
        Stack<Map<String, Integer>> stack = new Stack<>();
        //整个数列的起止下标 以哈希的形式入栈
        Map rootParam = new HashMap();
        rootParam.put("startIndex", startIndex);
        rootParam.put("endIndex", endIndex);
        stack.push(rootParam);
        //循环结束条件 栈为空时结束
        while(!stack.isEmpty()){
            //栈顶元素出栈 得到起止下标
            Map<String, Integer> param = stack.pop();
            //得到基准元素位置
            int pivot = partition2(arr, param.get("startIndex"), param.get("endIndex"));
            //根据基准元素分成两部分 每一部分的起止下标入栈
            if(param.get("startIndex") < pivot - 1){
                Map<String, Integer> leftParam = new HashMap<>();
                leftParam.put("startIndex", param.get("startIndex"));
                leftParam.put("endIndex", pivot - 1);
                stack.push(leftParam);
            }
            if(param.get("endIndex") > pivot + 1){
                Map<String, Integer> leftParam = new HashMap<>();
                leftParam.put("startIndex", pivot + 1);
                leftParam.put("endIndex", param.get("endIndex"));
                stack.push(leftParam);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {4,7,6,5,3,2,8,1};
        int[] arr2 = {4,7,6,5,3,2,8,1};
        int[] arr3 = {4,7,6,5,3,2,8,1};
        int[] arr4 = {4,7,6,5,3,2,8,1};
        quickSort1(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
        quickSort2(arr2,0,arr.length-1);
        System.out.println(Arrays.toString(arr2));
        quickSort3(arr3,0,arr.length-1);
        System.out.println(Arrays.toString(arr3));
        quickSortWithStack(arr4,0,arr.length-1);
        System.out.println(Arrays.toString(arr4));
    }



    static class QuickSortReview{
        /*挖坑*/
        public static void quickSort(int[] arr, int startIndex, int endIndex){
            if(startIndex >= endIndex){
                return;
            }
            int pivot = partition(arr, startIndex, endIndex);
            quickSort(arr, startIndex, pivot-1);
            quickSort(arr, pivot+1, endIndex);
        }
        public static int partition(int[] arr, int startIndex, int endIndex){
            int pivot = arr[startIndex];
            int index = startIndex;
            while(startIndex <= endIndex){
                while(startIndex <= endIndex){
                    if(arr[endIndex] < pivot){
                        arr[startIndex] = arr[endIndex];
                        startIndex++;
                        index = endIndex;
                        break;
                    }
                    endIndex--;
                }
                while(startIndex <= endIndex){
                    if(arr[startIndex] > pivot){
                        arr[endIndex] = arr[startIndex];
                        endIndex--;
                        index = startIndex;
                        break;
                    }
                    startIndex++;
                }
            }
            arr[index] = pivot;
            return index;
        }

        /*双边指针*/
        public static void quickSort2(int[] arr, int startIndex, int endIndex){
            if(startIndex >= endIndex){
                return;
            }
            int pivot = partition2(arr, startIndex, endIndex);
            quickSort2(arr, startIndex, pivot-1);
            quickSort2(arr, pivot+1, endIndex);
        }
        public static int partition2(int[] arr, int startIndex, int endIndex){
            int pivot = arr[startIndex];
            int index = startIndex;
            while(startIndex != endIndex){
                while(endIndex > startIndex && arr[endIndex] >= pivot){
                    endIndex--;
                }
                while(endIndex > startIndex && arr[startIndex] <= pivot){
                    startIndex++;
                }
                if(startIndex < endIndex){
                    int temp = arr[startIndex];
                    arr[startIndex] = arr[endIndex];
                    arr[endIndex] = temp;
                }
            }
            int temp = arr[startIndex];
            arr[startIndex] = pivot;
            arr[index] = temp;
            return startIndex;
        }

        /*单边指针*/
        public static void quickSort3(int[] arr, int startIndex, int endIndex){
            if(startIndex >= endIndex){
                return;
            }
            int pivot = partition3(arr, startIndex, endIndex);
            quickSort3(arr, startIndex, pivot-1);
            quickSort3(arr, pivot+1, endIndex);
        }
        public static int partition3(int[] arr, int startIndex, int endIndex){
            int pivot = arr[startIndex];
            int mark = startIndex;
            for (int i = startIndex+1; i <= endIndex; i++) {
                if(arr[i] < pivot){
                    mark++;
                    int temp = arr[mark];
                    arr[mark] = arr[i];
                    arr[i] = temp;
                }
            }
            int temp = arr[mark];
            arr[mark] = pivot;
            arr[startIndex] = temp;
            return mark;
        }

        public static void quickSortWithStack(int[] arr, int startIndex, int endIndex){
            Stack<Map<String, Integer>> stack = new Stack<>();
            Map<String, Integer> map = new HashMap();
            map.put("start", startIndex);
            map.put("end", endIndex);
            stack.push(map);
            while(!stack.isEmpty()){
                Map<String, Integer> param = stack.pop();
                int pivot = partition2(arr, param.get("start"), param.get("end"));
                if(param.get("start") < pivot - 1){
                    Map<String, Integer> leftParam = new HashMap<>();
                    leftParam.put("start", startIndex);
                    leftParam.put("end", pivot-1);
                    stack.push(leftParam);
                }
                if(param.get("end") > pivot+1){
                    Map<String, Integer> rightParam = new HashMap<>();
                    rightParam.put("start", pivot + 1);
                    rightParam.put("end", endIndex);
                    stack.push(rightParam);
                }
            }
        }



        public static void main(String[] args) {
            int[] arr = {4,7,6,5,3,2,8,1};
            quickSortWithStack(arr,0,arr.length-1);
            System.out.println(Arrays.toString(arr));
        }
    }

    static class QuickSortMd{
        public static void quickSort(int[] arr, int startIndex, int endIndex){
            if(startIndex >= endIndex){
                return;
            }
            int pivot = partition(arr, startIndex, endIndex);
            quickSort(arr, 0, pivot - 1);
            quickSort(arr, pivot + 1, endIndex);
        }
        public static int partition(int[] arr, int startIndex, int endIndex){
            int pivot = arr[endIndex];
            int left = startIndex;
            int right = endIndex;
            while(left != right){
//                while(left < right && arr[right] >= pivot){
//                    right--;
//                }
                while(left < right && arr[left] <= pivot){
                    left++;
                }
                while(left < right && arr[right] >= pivot){
                    right--;
                }
                if(left < right){
                    int temp = arr[left];
                    arr[left] = arr[right];
                    arr[right] = temp;
                }
            }
            int temp = arr[left];
            arr[left] = arr[endIndex];
            arr[endIndex] = temp;
            return left;
        }

        public static void main(String[] args) {
            int[] arr = {4,7,6,5,3,2,8,1};
            //int[] arr = {1,2};
            quickSort(arr,0,arr.length-1);
            System.out.println(Arrays.toString(arr));
        }
    }
}
