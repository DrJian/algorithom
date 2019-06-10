package com.hotj.java.algorithom.search;

import org.springframework.util.Assert;

/**
 * @author hongjian@youzan.com
 * @date 2019/4/12
 */
public class BinarySearch {
    public static void main(String[] args) {
        //一般二分查找
        int[] arr = {1, 3, 4, 6, 8};
        Assert.isTrue(searchSpecifiedVal(arr, 3) == 1);
        Assert.isTrue(searchSpecifiedVal(arr, 6) == 3);
        Assert.isTrue(searchSpecifiedVal(arr, 5) == -1);

        //查找出现的第一个位置
        int[] arr1 = {1, 3, 3, 3, 5, 6};
        Assert.isTrue(searchFisrtSpecifiedVal(arr1, 3) == 1);

        //查找最后一个出现位置
        Assert.isTrue(searchLastSpecifiedVal(arr1, 3) == 3);

        //查找环形数组
        int[] arrCycle = {8, 10, 13, 2, 4, 6};
        Assert.isTrue(searchCycleArray(arrCycle, 6) == 5);
        Assert.isTrue(searchCycleArray(arrCycle, 10) == 1);
        Assert.isTrue(searchCycleArray(arrCycle, 8) == 0);
        Assert.isTrue(searchCycleArray(arrCycle, 2) == 3);
        Assert.isTrue(searchCycleArray(arrCycle, 13) == 2);
        Assert.isTrue(searchCycleArray(arrCycle, 4) == 4);
        Assert.isTrue(searchCycleArray(arrCycle, 9) == -1);
        Assert.isTrue(searchCycleArray(arrCycle, 1) == -1);
        Assert.isTrue(searchCycleArray(arrCycle, 15) == -1);
    }

    private static int searchSpecifiedVal(int[] arr, int val) {
        int p = 0, q = arr.length - 1;
        while (p <= q) {
            int mid = p + ((q - p) >>> 1);
            if (val < arr[mid]) {
                q = mid - 1;
            } else if (val == arr[mid]) {
                return mid;
            } else {
                p = mid + 1;
            }
        }

        return -1;
    }

    private static int searchFisrtSpecifiedVal(int[] arr, int val) {
        int p = 0, q = arr.length - 1;

        while (p <= q) {
            int mid = p + ((q - p) >>> 1);
            if (val < arr[mid]) {
                q = mid - 1;
            } else if (val == arr[mid]) {
                if (mid - 1 >= 0 && arr[mid - 1] == val) {
                    q = mid - 1;
                } else {
                    return mid;
                }
            } else {
                p = mid + 1;
            }
        }

        return -1;
    }

    private static int searchLastSpecifiedVal(int[] arr, int val) {
        int p = 0, q = arr.length - 1;

        while (p <= q) {
            int mid = p + ((q - p) >>> 1);
            if (val < arr[mid]) {
                q = mid - 1;
            } else if (val == arr[mid]) {
                if (mid + 1 < arr.length && arr[mid + 1] == val) {
                    p = mid + 1;
                } else {
                    return mid;
                }
            } else {
                p = mid + 1;
            }
        }

        return -1;
    }

    private static int searchCycleArray(int[] arr, int val) {
        int p = 0, q = arr.length - 1;

        if (val == arr[0]) {
            return 0;
        }

        while (p <= q) {
            int mid = p + ((q - p) >>> 1);

            if (val < arr[mid]) {
                if (arr[mid] > arr[mid + 1] && val < arr[0]) {
                    p = mid + 1;
                } else if (arr[mid] > arr[mid+1] && val > arr[0]) {
                    q = mid - 1;
                } else {
                    q = mid - 1;
                }
            } else if (val == arr[mid]) {
                return mid;
            } else {
                p = mid + 1;
            }
        }

        return -1;
    }
}
