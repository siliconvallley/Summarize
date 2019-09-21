package com.dh.summarize.fragment.algorithm.data_struct

/**
 * @author 86351
 * @date 2019/9/11
 * @description
 */
class TestOne private constructor() {
    companion object {
        fun getInstance() = TestOneHolder.instance
    }

    private object TestOneHolder {
        val instance = TestOne()
    }

    /**
     * 双重循环
     */
    fun twoSum(nums: IntArray, target: Int): IntArray {
        repeat(nums.size) {
            for (i in (it + 1) until nums.size) {
                if (nums[it] == target - nums[i]) {
                    return intArrayOf(it, i)
                }
            }
        }
        return intArrayOf()
    }

    /**
     * Map保存  值-》index
     */
    fun twoSum1(nums: IntArray, target: Int): IntArray {
        val map: MutableMap<Int, Int> = hashMapOf()
        repeat(nums.size) {
            val result = target - nums[it]
            if (map.containsKey(result)) {
                return intArrayOf(it, map[result]!!)
            }
            map[nums[it]] = it
        }
        return intArrayOf()
    }

    fun twoSum2(nums: IntArray, target: Int): IntArray {
        /*val temp: MutableList<Int> = arrayListOf()
        repeat(nums.size) {
            val result = target - nums[it]
            if (temp.contains(result)) {
                return intArrayOf(it, temp.indexOf(result))
            }
            temp.add(nums[it])
        }*/
        val map: MutableMap<Int, Int> = hashMapOf()
        repeat(nums.size) {
            val result = target - nums[it]
            if (map.containsKey(result)) {
                return intArrayOf(it, map[result]!!)
            }
            map[nums[it]] = it
        }
        return intArrayOf()
    }

    fun revertLink(head: Node?): Node? {
        var curNode = head
        var preNode: Node? = null
        while (curNode != null) {
            val tempNode = curNode.next
            curNode.next = preNode
            preNode = curNode
            curNode = tempNode
        }
        return preNode
    }
}