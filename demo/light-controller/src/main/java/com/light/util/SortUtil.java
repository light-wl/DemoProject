package com.light.util;

import java.util.Arrays;

/**
 * @Author light
 * @Date 2023/4/24
 * @Desc 排序工具类，主要介绍用处较多排序的功能，如快速排序，外部排序等
 * 1、当对一个文件进行排序时，可以采用基础版本排序，如快速排序
 * 2、当一个文件较大，如10G，则可以采用进阶版本，外部排序
 * 3、当再大一点，如100G，则可以加入多线程，参考MapReduce的设计
 * 4、如果再大，那么直接上大数据吧，用正版 MapReduce
 * <p>
 * 插入类排序：简单插入排序，升级为-希尔排序
 * 选择类排序：简单选择排序，升级为-堆排序
 * 交换类排序：冒泡排序，升级为-快速排序
 * 归并排序：很多复杂的排序可以进行分块解决。
 * 桶排序：只有特定数据类型可以，可以在O(n)时间完成排序，需要消耗部分桶空间。
 **/
public class SortUtil {
    public static void main(String[] args) {
        /*快速排序测试*/
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        System.out.println("快速排序前" + Arrays.toString(arr));
//        quickSort(arr, 0, arr.length - 1);
        System.out.println("快速排序后" + Arrays.toString(arr));

        /*堆排序测试*/
        System.out.println("堆排序前" + Arrays.toString(arr));
//        heapSort(arr);
        System.out.println("堆排序后" + Arrays.toString(arr));

        // 测试冒泡排序
        System.out.println("冒泡排序前" + Arrays.toString(arr));
//        bulbSort(arr);
        System.out.println("冒泡排序后" + Arrays.toString(arr));

        // 选择排序
        System.out.println("选择排序前" + Arrays.toString(arr));
        selectSort(arr);
        System.out.println("选择排序后" + Arrays.toString(arr));
    }

    /**
     * 名称：冒泡排序
     * 稳定
     * 复杂度：O(N)^2
     * 优化1：添加flag标志，如果一次遍历都没有移动，则表示有序
     * 优化2：将最后移动的位置，作为结束的位置，可以节约排序次数
     */
    public static void bulbSort(int[] arr) {
        int len = arr.length;
        int dynamicLen = len - 1;
        int tempLen = 0;
        for (int i = 0; i < len - 1; i++) {
            // 优化1：如果没有移动，则表示已经有序
            boolean isMoved = false;
            // 优化2：动态dynamicLen
            for (int j = 0; j < dynamicLen; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    isMoved = true;
                    tempLen = j;
                }
            }
            dynamicLen = tempLen;
            if (!isMoved) {
                break;
            }
        }
    }

    /**
     * 名称：选择排序
     * 稳定
     * 复杂度：O(N)^2
     */
    public static void selectSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            int minimumIndex = i;
            for (int j = i; j < len - 1; j++) {
                if (arr[j] < arr[minimumIndex]) {
                    minimumIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minimumIndex];
            arr[minimumIndex] = temp;
        }
    }

    /**
     * 快速排序
     * 5.1 hoare版本(左右指针法)
     * 思路：
     * 1、选出一个key，一般是最左边或是最右边的。
     * 2、定义一个begin和一个end，begin从左向右走，end从右向左走。（需要注意的是：若选择最左边的数据作为key，则需要end先走；若选择最右边的数据作为key，则需要bengin先走）。
     * 3、在走的过程中，若end遇到小于key的数，则停下，begin开始走，直到begin遇到一个大于key的数时，将begin和right的内容交换，end再次开始走，如此进行下去，直到begin和end
     * 最终相遇，此时将相遇点的内容与key交换即可。（选取最左边的值作为key）
     * 4.此时key的左边都是小于key的数，key的右边都是大于key的数
     * 5.将key的左序列和右序列再次进行这种单趟排序，如此反复操作下去，直到左右序列只有一个数据，或是左右序列不存在时，便停止操作，此时此部分已有序
     */
    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = partition(arr, left, right);
        quickSort(arr, left, mid - 1);
        quickSort(arr, mid + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int key = left;
        while (left < right) {
            while (left < right && arr[right] >= arr[key]) {
                right--;
            }
            while (left < right && arr[left] <= arr[key]) {
                left++;
            }
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
        int temp = arr[key];
        arr[key] = arr[left];
        arr[left] = temp;
        return left;
    }

    /**
     * 堆排序
     */
    public static void heapSort(int[] arr) {
        int temp = 0;

        // 第一次调整大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        /**
         * 将堆项元素与末尾元素交换，将最大元素"沉"到数组末端;
         * 重新调整结构，使其满足堆定义，然后继续交换堆项元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
         */
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
    }


    /**
     * 将一个数组（二叉树）调整成一个大根堆
     * 功能：完成将以i对应的非叶子结点的树调整成大顶堆
     * 举例int arr[]={4, 6,8,5,9};=>i=1=> adjustHeap=>得到{4,9,8,5, 6}
     * 如果我们再次调用adjustHeap 传入的是i=0=>得到{4,9, 8,5,6}=> {9,6,8,5, 4}
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子结点在数组中索引
     * @param length 表示对多少个元素继续调整，length 是在逐渐的减少
     */
    private static void adjustHeap(int[] arr, int i, int length) {

        int temp = arr[i];//先取出当前元素的值，保存在临时变量
        //开始调整.
        //说明:k=i*2+1 k是i结点的左子结点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > arr[i]) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }

    /**
     * 外部排序
     * 背景：外部排序，乱序数字就不是在内存中加载为数组了，而是存在文件上的。Java需要IO读取文件内容，将文件中的数字经过处理，
     * 然后IO输出中间文件及最后的排好序的文件，完成整个外部排序过程。
     * 参考：https://blog.csdn.net/weixin_39448458/article/details/103025715
     * */
}
