package com.hotj.algorithom.common;

/**
 * @author hongjian@youzan.com
 * @date 2019/4/4
 */
public class ArrayUtil {
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] >= arr[i + 1]) {
                return false;
            }
        }

        return true;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
