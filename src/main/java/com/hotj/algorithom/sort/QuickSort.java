package com.hotj.algorithom.sort;

import com.hotj.algorithom.common.ArrayUtil;
import org.apache.commons.lang3.Range;
import org.springframework.util.Assert;

/**
 * 快排实现
 *
 * @author hongjian@youzan.com
 * @date 2019/4/4
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {1, 4, 3, 2, 5};
        quickSort(arr);
        Assert.isTrue(ArrayUtil.isSorted(arr));

        int[] arr2 = {1, 6, 2, 3};
        quickSort(arr2);
        Assert.isTrue(ArrayUtil.isSorted(arr2));

        int[] arr3 = {1, 2, 3, 4, 5};
        quickSort(arr3);
        Assert.isTrue(ArrayUtil.isSorted(arr3));

        int[] arr4 = {5, 4, 3, 2, 1};
        quickSort(arr4);
        Assert.isTrue(ArrayUtil.isSorted(arr4));

        int[] arr5 = {4, 3, 2, 1};
        quickSort(arr5);
        Assert.isTrue(ArrayUtil.isSorted(arr5));

        int[] arr6 = {1, 2, 3, 4};
        quickSort(arr6);
        Assert.isTrue(ArrayUtil.isSorted(arr6));
    }

    public static void quickSort(int[] arr) {
        if (arr == null) {
            return;
        }

        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int p, int q) {
        if (p >= q) {
            return ;
        }

        int r = partition(arr, p, q);
        sort(arr, p, r - 1);
        sort(arr, r + 1, q);
    }

    private static int partition(int[] arr, int p, int q) {
        int pivot = calculatePivot(arr, p , q);

        //数组中 >= pivot值数据的最小下标
        int valueGePivotIndex = p;

        //将选出的pivot放到数组的最后
        ArrayUtil.swap(arr, q, pivot);

        //pivot对应的值不参与比较
        for (; p <= q - 1; p++) {
            if (arr[p] < arr[q]) {
                ArrayUtil.swap(arr, valueGePivotIndex, p);
                valueGePivotIndex++;
            }
        }

        //将pivot与>=自身的数据进行swap
        ArrayUtil.swap(arr, valueGePivotIndex, p);

        return valueGePivotIndex;
    }

    private static int calculatePivot(int[] arr, int p, int q) {
        //三位取中法
        int midVal = arr[(p + q) >>> 1];
        int mid = (p + q) >>> 1;
        Integer pivot;
        if (Range.between(arr[p], arr[q]).contains(midVal) || Range.between(arr[q], arr[p]).contains(midVal)) {
            pivot = mid;
        } else if (Range.between(arr[p], midVal).contains(arr[q]) || Range.between(midVal, arr[p]).contains(arr[q])) {
            pivot = arr[q];
        } else {
            pivot = arr[p];
        }

        Assert.notNull(pivot);

        return mid;
    }

}
