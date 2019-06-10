package com.hotj.java.algorithom.linklist;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.util.Assert;

/**
 * 使用单向链表来确定给定链表是否为回文数
 *
 * @author hongjian
 * @date 2019/03/18
 */
public class Palindrome {
    public static void main(String[] args) {
        Assert.isTrue(!isPalindrome(build("")));
        Assert.isTrue(!isPalindrome(build(null)));

        Assert.isTrue(isPalindrome(build("1")));
        Assert.isTrue(isPalindrome(build("121")));

        Assert.isTrue(!isPalindrome(build("123")));

        Assert.isTrue(isPalindrome(build("11")));
        Assert.isTrue(isPalindrome(build("1221")));

        Assert.isTrue(!isPalindrome(build("1212")));

        System.out.println("success"
                + " Class:" + new Throwable().getStackTrace()[0].getClassName()
                + " Line:" + new Throwable().getStackTrace()[0].getLineNumber());
    }

    private static LinkedList build(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < str.length(); i++) {
            linkedList.add(str.charAt(i));
        }

        return linkedList;
    }

    private static boolean isPalindrome(LinkedList list) {
        if (list == null || list.head() == null) {
            return false;
        }

        if (list.head().next() == null) {
            return true;
        }

        Node singleStepPtr, twoStepPtr;
        singleStepPtr = list.head();
        twoStepPtr = singleStepPtr.next();

        //找到中点
        boolean isEven;
        while (true) {
            if (twoStepPtr.next() == null) {
                isEven = true;
                break;
            } else if (twoStepPtr.next().next() == null) {
                isEven = false;
                break;
            }

            singleStepPtr = singleStepPtr.next();
            twoStepPtr = twoStepPtr.next().next();
        }

        //singleStepPtr的位置为另一半字符串的起点
        if (isEven) {
            singleStepPtr = singleStepPtr.next();
        } else {
            singleStepPtr = singleStepPtr.next().next();
        }

        Node comparePtr = list.head();
        while (singleStepPtr != null) {
            if (comparePtr.val() != singleStepPtr.val()) {
                return false;
            }

            singleStepPtr = singleStepPtr.next();
            comparePtr = comparePtr.next();
        }

        return true;
    }
}

@Accessors(fluent = true)
@Getter
@Setter
class Node {
    private char val;

    private Node next;
}

@Accessors(fluent = true)
@Getter
class LinkedList {
    private Node head;

    private Node tail;

    public void add(char val) {
        if (head == null) {
            head = tail = new Node().val(val);
            return;
        }

        tail.next(new Node().val(val));
    }

    public void clear() {
        head = tail = null;
    }
}