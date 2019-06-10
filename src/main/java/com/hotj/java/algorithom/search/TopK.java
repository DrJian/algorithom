package com.hotj.java.algorithom.search;

import com.hotj.java.algorithom.common.ArrayUtil;
import org.springframework.util.Assert;

/**
 * 查找数组中的TopK元素
 *
 * @author hongjian@youzan.com
 * @date 2019/4/12
 */
public class TopK {
    public static void main(String[] args) {
        int[] arr = {4, 2, 3, 6, 1};
        Assert.isTrue(findTopK(arr, 4) == 6);
        Assert.isTrue(findTopK(arr, 0) == 1);
    }

    private static int findTopK(int[] arr, int index) {
        int p = 0;
        int q = arr.length - 1;

        //数组中 >= pivot值数据的最小下标
        while (p <= q) {
            int valGtPivot = p;
            if (p == q) {
                return arr[p];
            }

            //以数组最后一个元素为pivot
            for (int i = p; i < q; i++) {
                if (arr[i] < arr[q]) {
                    ArrayUtil.swap(arr, i, valGtPivot);
                    valGtPivot++;
                }
            }

            ArrayUtil.swap(arr, q, valGtPivot);

            if (index < valGtPivot) {
                q = valGtPivot - 1;
            } else if (index == valGtPivot) {
                return arr[valGtPivot];
            } else {
                p = valGtPivot + 1;
            }
        }

        return -1;
    }
}
