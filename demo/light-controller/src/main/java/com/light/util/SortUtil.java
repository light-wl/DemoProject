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
 **/
public class SortUtil {
    public static void main(String[] args) {
        /*快速排序测试*/
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        System.out.println("快速排序前" + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("快速排序后" + Arrays.toString(arr));

        /*堆排序测试*/
        System.out.println("堆排序前" + Arrays.toString(arr));
        heapSort(arr);
        System.out.println("堆排序后" + Arrays.toString(arr));
    }

    /**
     * 快速排序
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
