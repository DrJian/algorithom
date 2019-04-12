package com.hotj.algorithom.sort;

import com.hotj.algorithom.common.ArrayUtil;
import org.springframework.util.Assert;

/**
 * @author hongjian@youzan.com
 * @date 2019/4/7
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 4};
        sort(arr);
        Assert.isTrue(ArrayUtil.isSorted(arr));

        int[] arr1 = {7, 2, 8, 10, 6 ,1};
        sort(arr1);
        Assert.isTrue(ArrayUtil.isSorted(arr1));
    }

    private static void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int p, int q) {
        if (p >= q) {
            return;
        }

        int mid = (p + q) >>> 1;
        mergeSort(arr, p, mid);
        mergeSort(arr, mid + 1, q);

        int[] tmp = new int[q - p + 1];

        //数据merge
        int p2 = p;
        int q2 = mid + 1;
        int k = 0;
        while (p2 <= mid && q2 <= q) {
            if (arr[p2] <= arr[q2]) {
                tmp[k++] = arr[p2++];
            } else {
                tmp[k++] = arr[q2++];
            }
        }

        while (p2 <= mid) {
            tmp[k++] = arr[p2++];
        }

        while (q2 <= q) {
            tmp[k++] = arr[q2++];
        }

        int i = 0;
        while (p <= q) {
            arr[p++] = tmp[i++];
        }
    }
}
