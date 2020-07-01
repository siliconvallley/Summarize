package com.dh.summarize.fragment.algorithm

import android.view.View
import com.dh.summarize.R
import com.dh.summarize.base.BaseFragment
import com.dh.summarize.fragment.algorithm.data_struct.CustomLinkedList
import com.dh.summarize.fragment.algorithm.data_struct.Node
import com.dh.summarize.fragment.algorithm.data_struct.TestOne
import com.dh.summarize.utils.algorithm.AlgorithmUtils
import com.dh.utils_library.utils.LogUtils
import kotlinx.android.synthetic.main.test_algorithm_fragment_layout.*
import java.math.BigInteger


const val TAG: String = "TestAlgorithmFragment"

/**
 * @author 86351
 * @date 2019/9/11
 * @description
 */
class TestAlgorithmFragment : BaseFragment(), View.OnClickListener {

    companion object {
        fun getInstance() = TestAlgorithmFragment()
        private const val TAG = "TestAlgorithmFragment"
    }

    override fun getLayoutId(): Int {
        return R.layout.test_algorithm_fragment_layout
    }

    override fun initViews(view: View) {
    }

    override fun initListener() {
        btSum.setOnClickListener(this)
        btBinary.setOnClickListener(this)
        btOrAndXor.setOnClickListener(this)
        btMostFrequent.setOnClickListener(this)
        btReverseLinkedList.setOnClickListener(this)
    }

    override fun initData() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btSum -> {
                val twoSum = TestOne.getInstance().twoSum1(intArrayOf(2, 7, 11, 15), 9)
                for (index in twoSum) {
                    LogUtils.d(TAG, "数组:$index")
                }
            }
            R.id.btBinary -> {
                LogUtils.d(TAG, "十进制转二进制: ${decimalToBinary(53)}")
                LogUtils.d(TAG, "二进制转十进制: ${binaryToDecimal("110101")}")
            }
            R.id.btOrAndXor -> {
                LogUtils.d(TAG, "二进制或: ${or(53, 35)}")
                LogUtils.d(TAG, "二进制与: ${and(53, 35)}")
                LogUtils.d(TAG, "二进制异或: ${xor(53, 35)}")
                LogUtils.d(TAG, "二进制异或，可以用来判断两个数字是否相同: ${xor(53, 53) == 0}")
                LogUtils.d(TAG, "迭代法：${getNumber(63)}")
            }
            R.id.btMostFrequent -> {
                val arr = intArrayOf(2, 20, 3, 6, 2, 3, 2)
                AlgorithmUtils.getInstance().getArrayMostFrequent(arr)
                AlgorithmUtils.getInstance().getArrayMostFrequentForMap(arr)
            }
            R.id.btReverseLinkedList -> {
                val cusLinkedList = CustomLinkedList()
                cusLinkedList.insert(0, 0)
                cusLinkedList.insert(1, 1)
                cusLinkedList.insert(2, 2)
                cusLinkedList.insert(3, 3)
                cusLinkedList.insert(4, 4)
                cusLinkedList.insert(5, 5)
                cusLinkedList.insert(6, 6)
                cusLinkedList.insert(7, 7)
                cusLinkedList.insert(8, 8)

                val node =
                    AlgorithmUtils.getInstance().reverseLinkedList(cusLinkedList.node)
                val nodeStr = cusLinkedList.outNode(node)
                LogUtils.d(TAG, "反转链表: $nodeStr")
            }
        }
    }


    fun outPut(head: Node?): String? {
        val builder = StringBuilder()
        var tempNode: Node? = head
        while (tempNode != null) {
            builder.append(tempNode.data).append(" ")
            tempNode = tempNode.next
        }
        return builder.toString()
    }

    /**
     * 十进制转二进制
     */
    private fun decimalToBinary(decimal: Int): String {
        val bigInteger = BigInteger(decimal.toString())
        //转换为2进制
        return bigInteger.toString(2)
    }

    /**
     * 二进制转十进制
     */
    private fun binaryToDecimal(binary: String): Int {
        val bigInteger = BigInteger(binary, 2)
        // 默认转换为10进制
        return bigInteger.toString().toInt()
    }

    /**
     * Java的二进制或 "|"
     */
    private fun or(num1: Int, num2: Int): Int {
        return num1 or num2
    }

    /**
     * Java的二进制与 "&"
     */
    private fun and(num1: Int, num2: Int): Int {
        return num1 and num2
    }

    /**
     * Java的二进制异或 "^"
     */
    private fun xor(num1: Int, num2: Int): Int {
        return num1 xor num2
    }

    private fun getNumber(num: Int): Long {
        var sum = 0L
        var currentNum = 1L
        sum += currentNum
        for (i in 2..num) {
            currentNum *= 2
            sum += currentNum
        }
        return sum
    }


}