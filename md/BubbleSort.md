# 冒泡排序
每一轮比较都能确定这轮的最大数
## 简单实现
~~~
public class BubbleSort{
    public void sort(int[] arr){
        for(int i = 0; i < arr.length-1; i++){
            for(int j = 0; j < arr.length - i - 1; j++){
                if(arr[j] > arr[j+1]){
                    int tmep = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        //自己写的打印方法
        print(arr);
    }
}
~~~
## 添加有序标志位
添加有序标志位, 一轮排序都是有序的就可以直接跳出大循环, 不用再继续了
~~~
public static void sort(int[] arr){
    for(int i = 0; i < arr.length-1; i++){
        boolean isSorted = true;
        for(int j = 0; j < arr.length - i - 1; j++){
            if(arr[j] > arr[j+1]){
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
                isSorted = false;
            }
        }
        if(isSorted){
            break;
        }
    }
    print(arr);
}
~~~
## 添加有序区界定
添加有序区界定(记录上一次交换的位置, 下次比到这里就可以了)  
有序区: 有顺序的区间,数组的后面 第一次1, 第二次2, 第三次3...
~~~
public static void sort(int[] arr){
    //记录最后一次交换的位置
    int lastExchangeIndex = 0;
    //无序部分数组的边界 每次比较比较到这里就可以
    int sortBorder = arr.length - 1;
    for(int i = 0; i < arr.length-1; i++){
        boolean isSorted = true;
        for(int j = 0; j < sortBorder; j++){
            if(arr[j] > arr[j+1]){
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
                lastExchangeIndex = j;
                isSorted = false;
            }
        }
        sortBorder = lastExchangeIndex;
        if(isSorted){
            break;
        }
    }
    print(arr);
}
~~~
## 鸡尾酒排序
大循环里嵌套两轮小循环 一轮从左向右 一轮从右向左 适用于大部分有序  
比如: 2 3 4 5 6 7 8 9 1 等情况  
~~~
public static void sort(int[] arr){
    for (int i = 0; i < arr.length / 2; i++) {
        boolean isSorted = true;
        for (int j = i; j < arr.length - i - 1; j++) {
            if(arr[j] > arr[j+1]){
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
                isSorted = false;
            }
        }
        if(isSorted){
            break;
        }
        isSorted = true;
        for (int j = arr.length - i - 1; j > i ; j--) {
            if(arr[j] < arr[j-1]){
                int temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
                isSorted = false;
            }
        }
        if(isSorted){
            break;
        }
    }
    print(arr);
}
~~~
## 鸡尾酒排序(添加有序区)
也可以添加有序区, 上面经过外一轮有序区加1, 鸡尾酒因为外一轮里有两轮, 所以应该有两个有序区界定
~~~
public static void sort(int[] arr){
    int lastRightExchangeIndex = 0;
    int lastLeftExchangeIndex = 0;
    int rightSortBorder = arr.length -1;
    int leftSortBorder = 0;
    for (int i = 0; i < arr.length / 2; i++) {
        boolean isSorted = true;
        for (int j = i; j < rightSortBorder; j++) {
            if(arr[j] > arr[j+1]){
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
                lastRightExchangeIndex = j;
                isSorted = false;
            }
        }
        rightSortBorder = lastRightExchangeIndex;
        if(isSorted){
            break;
        }
        isSorted = true;
        for (int j = arr.length - i - 1; j > leftSortBorder ; j--) {
            if(arr[j] < arr[j-1]){
                int temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
                lastLeftExchangeIndex = j;
                isSorted = false;
            }
        }
        leftSortBorder = lastLeftExchangeIndex;
        if(isSorted){
            break;
        }
    }
    print(arr);
}
~~~
# 参考资料
小灰的算法之旅
