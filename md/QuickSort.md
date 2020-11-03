# 快速排序
同冒泡排序一样, 快速排序也是**交换**排序, 但是采用了分治的思想, 所以效率比冒泡排序高很多  
冒泡排序每一轮都找出最大的元素到数列的一端;  
而快排挑选一个基准元素, 将比基准元素大移动到数列一端, 比基准元素小的移动到另一端, 将数列分成两部分  
下面几种实现, 推荐**指针交换法(双边指针)**, 容易理解
# 时间复杂度
平均时间复杂度为O(nlogn), 最坏时间复杂度为O(n2)
## 挖坑法实现
基准元素位置相当于一个坑, 右侧的小于基准元素就右侧这个位置为新坑; 左侧的大于基准元素, 左侧这个则为新坑(用新坑的值填旧坑的值, 因为最开始的坑是基准元素, 所以要保存基准元素值, 最后替换最后坑的值)
~~~
//挖坑法 递归实现 双向
public static void quickSort1(int[] arr, int startIndex, int endIndex){
    if(startIndex >= endIndex){
        return;
    }
    int pivotIndex = partition1(arr, startIndex, endIndex);
    quickSort1(arr, startIndex, pivotIndex-1);
    quickSort1(arr,pivotIndex+1, endIndex);
}
//挖坑法
public static int partition1(int[] arr, int startIndex, int endIndex){
    //取第一个元素作为基准元素
    int pivot = arr[startIndex];
    int left = startIndex;
    int right = endIndex;
    //坑的位置!!!!!!!! 初始等于pivot的位置
    int index = startIndex;

    while(right >= left){
        //right指针从右向左移动
        while(right >= left){
            if(arr[right] < pivot){
                // 用新坑的值填旧坑的值 新坑为right位置 结束right指针本次循环
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
                // 用新坑的值填旧坑的值 新坑为left位置 结束left指针本次循环
                arr[right] = arr[left];
                index = left;
                right--;
                break;
            }
            left++;
        }
    }
    //用基准元素替换最后坑的位置
    arr[index] = pivot;
    return index;
}
~~~
## 指针交换法实现(双边指针)
指针交换法, 双边遍历, 右边找小于pivot的停下, 左边找大于pivot的停下, 然后左右交换, 最后用pivot和重合位置的元素交换  
**注意: pivot元素选取影响处理顺序, 见代码注释详细说明**
~~~
//指针交换法 双边指针 递归实现 双向
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
        // 理解是先处理left指针可能会多换出去一步, 最后pivot和重合点交换的时候不对了
        // pivot如果选择endIndex处的元素, 则先处理left, 后处理right可以
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
~~~
## 指针交换法实现(单边指针)
~~~
//指针交换法 递归实现 单边
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
~~~
## 指针交换法(双边)(使用栈实现)
绝大多数递归实现的问题都可以使用栈来实现, 因为递归调用本身就是一个函数栈  
进入一个新方法, 相当于入栈; 方法返回, 相当于出栈; 递归转化栈, 在栈中存储每一次方法调用的参数
~~~
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
        //得到基准元素位置 partition2()方法见上面快排双边指针递归实现的partition2()
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
~~~
# 参考资料
本文主要是用于自己学习的笔记  
参考来源: 小灰的算法之旅

  
 
