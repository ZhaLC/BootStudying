[TOC] 
堆排序 用代码来实现优先级队列
# 一、二叉堆
二叉堆本质上是一棵**完全二叉树**, 分为最大堆和最小堆两种; 二叉堆的根节点叫做**堆顶**。
二叉堆本质虽然是完全二叉树, 但是地层没有实现链表(链式存储)实现, 而是使用**数组(顺序存储)**实现。**根据二叉树的性质, 假设父节点的索引为i, 则左孩子所以为2*i+1, 右孩子索引为2*i+2**。
## 1.最大堆
任意一个父节点的值大于等于左右子节点的值。
## 2.最小堆
任意一个父节点的值小于等于左右子节点的值。
## 3.二叉堆相关操作
构建二叉堆, 需要依靠二叉树的自我调整, 对于二叉堆, 主要有插入节点、删除节点、构建二叉堆几种操作。以最小堆为例, 下面详细介绍一下上述三种操作。
### 1) 插入节点
先将节点插入到二叉树的最后一个位置, 和其父节点比较, 如果小于父节点, 则进行交换, 迭代进行到大于等于父节点时结束。
~~~
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
~~~
### 2) 删除节点
二叉堆的节点删除过程和插入过程相反, 所删除的是处于堆顶的节点, 为了维持堆的结构, 将最后的节点补到原堆顶的位置, 新堆顶的节点和它的左右孩子节点比较, 如果左右孩子都比新堆顶小, 则选小的和堆顶交换, 迭代进行下沉, 直到小于左右孩子为止。
~~~
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
~~~
### 3) 构建二叉堆
构建二叉堆, 也就是把一个无序的完全二叉树调整为二叉堆, 本质上是让所有非叶子节点依次下沉。从最后一个非叶子节点开始。
~~~
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
~~~
# 二、堆排序
## 1、代码实现
根据二叉堆的性质, 堆排序可以分为两步:
1. 将无序数组构建成二叉堆。
2. 循环删除堆顶元素，移到集合尾部(交换位置)，调节堆产生新的堆顶。所以从小到大排序应该构建最大堆。
~~~
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
~~~
## 2、复杂度分析
### 1) 空间复杂度
堆排序都是原来的基础上进行, 没有开辟额外的空间, 所以空间复杂度为O(1)。
### 2) 时间复杂度
下沉调整是基础, 假设有n个元素, 最坏时间复杂度等于二叉树的高度, 为 O(logn)。
1. 把无序数组构建成二叉堆, 要进行 n/2 次下沉操作, 时间复杂度 O(nlogn);
2. 循环删除堆顶构建新的二叉堆, 进行 n-1 次循环, 每次执行下沉操作, 时间复杂度 O(nlogn)。
上述两步操作是并列关系, 所以时间复杂度是 O(nlogn)。
### 3) 对比快排
* 平均时间复杂度都是 O(nlogn), 都是不稳定排序
* 快排最坏时间复杂度为 O(n^2), 堆排稳定在 O(nlogn)
* 快排递归、非递归空间复杂度都是 O(n), 堆排是 O(1)
# 三、优先级队列
* **最大优先队列:** 无论入队顺序, 当前最大元素先出列。
* **最小优先队列:** 无论入队顺序, 当前最小元素先出列。  
优先级队列也是二叉堆的一个典型应用, 线性结构也能满足, 但是最坏时间复杂度是 O(n);
* **入队操作:** 插入新节点, 上浮到合适位置。
* **出队操作:** 原堆顶出队, 最后一个节点替换堆顶位置, 下沉到合适位置。   
根据上面二叉堆上浮、下沉时间复杂度都是 O(logn) 可知**优先级队列入队、出队时间复杂度都是 O(logn)**。
# 四、参考资料
《小灰的漫画算法之旅》





