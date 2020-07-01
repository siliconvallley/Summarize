package com.dh.summarize.utils.algorithm;

import com.dh.summarize.fragment.algorithm.data_struct.Node;
import com.dh.utils_library.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 86351
 * @date 2020/6/18
 * @description
 */
public class AlgorithmUtils {
    private static final String TAG = AlgorithmUtils.class.getSimpleName();

    private static class SingleHolder {
        public static AlgorithmUtils instance = new AlgorithmUtils();
    }

    public static AlgorithmUtils getInstance() {
        return SingleHolder.instance;
    }

    /**
     * 获取数组中出现最多的元素和次数
     *
     * @param arr
     * @return
     */
    public int getArrayMostFrequent(int[] arr) {
        int mostElement = 0;
        int tempCount = 0;
        int timeCount = 0;
        for (int i = 0; i < arr.length; i++) {
            tempCount = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    tempCount++;
                }
            }
            if (tempCount > timeCount) {
                timeCount = tempCount;
                mostElement = arr[i];
            }
        }
        LogUtils.d(TAG, "出现次数最多的元素: " + mostElement + "==>次数: " + timeCount);
        return timeCount;
    }

    /**
     * 获取数组中出现最多的元素和次数
     * 使用map优化
     *
     * @param arr
     * @return
     */
    public int getArrayMostFrequentForMap(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        int mostElement = 0;
        int count = 0;
        int timeCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) > timeCount) {
                timeCount = map.get(key);
                mostElement = key;
            }
            count++;
        }
        LogUtils.d(TAG + "使用Map优化", "出现次数最多的元素: " + mostElement + "==>最多元素次数: " + timeCount
                + "元素合并之后遍历次数: " + count);
        return timeCount;
    }

    /**
     * 反转链表
     * @param node
     * @return
     */
    public Node reverseLinkedList(Node node) {
        // 当前节点，将传递进来的头结点赋值给当前节点
        Node curNode = node;
        // 下一个节点
        Node nextNode;
        // 前一个节点
        Node preNode = null;
        while (curNode != null) {
            // 取出当前节点的下一个节点赋值给我们定义好的nextNode
            nextNode = curNode.next;
            // 将当前节点指向前一个节点实现反转
            curNode.next = preNode;
            // 将当前节点赋值给前一节点
            preNode = curNode;
            // 移动当前节点到下一个节点
            curNode = nextNode;
        }
        return preNode;
    }

    
}
