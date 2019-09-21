package com.dh.summarize.fragment.algorithm.data_struct;

import android.util.SparseIntArray;

import java.util.HashMap;
import java.util.Map;

public class Node {
    public int data;
    public Node next;

    public Node() {
    }

    public Node(int data) {
        this.data = data;
    }

    /*public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }*/

    /*public int[] twoSum(int[] nums, int target) {
        //SparseIntArray array = new SparseIntArray();
        Map<Integer, Integer> map = new HashMap<>(12);
        for (int i = 0; i < nums.length; i++) {
            int result = target - nums[i];
            if (map.containsKey(result)) {
                return new int[]{i, map.get(result)};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }*/
}
