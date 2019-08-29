package com.dh.summarize.fragment.algorithm

import android.view.View
import com.dh.summarize.R
import com.dh.summarize.base.BaseFragment
import com.dh.summarize.fragment.algorithm.data_struct.CustomArray
import com.dh.summarize.fragment.algorithm.data_struct.CustomLinkedList
import kotlinx.android.synthetic.main.array_fragment_layout.*

class ArrayFragment : BaseFragment(), View.OnClickListener {
    companion object {

        fun getInstance() = ArrayFragment()
    }

    override fun getLayoutId(): Int {
        return R.layout.array_fragment_layout
    }

    override fun initViews(view: View) {
        /*mActivity.window.decorView.viewTreeObserver.addOnDrawListener {

        }*/
    }

    override fun initListener() {
        bt_array_add.setOnClickListener(this)
        bt_array_del.setOnClickListener(this)
        bt_link_add.setOnClickListener(this)
        bt_link_del.setOnClickListener(this)
    }

    override fun initData() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_array_add -> {
                addArray()
            }
            R.id.bt_array_del -> {
                delArray()
            }
            R.id.bt_link_add -> {
                addLink()
            }
            R.id.bt_link_del -> {
                delLink()
            }
        }
    }

    private fun delLink() {
        val cusLinkedList = CustomLinkedList()
        cusLinkedList.insert(0,0)
        cusLinkedList.insert(1,1)
        cusLinkedList.insert(2,2)
        cusLinkedList.insert(3,3)
        cusLinkedList.insert(4,4)
        cusLinkedList.insert(5,5)
        cusLinkedList.insert(6,6)
        cusLinkedList.insert(7,7)
        cusLinkedList.insert(8,8)

        val removeNode = cusLinkedList.remove(5)

        tv_link_content.text = removeNode.data.toString()
    }

    private fun addLink() {
        val cusLinkedList = CustomLinkedList()
        cusLinkedList.insert(0,0)
        cusLinkedList.insert(1,1)
        cusLinkedList.insert(2,2)
        cusLinkedList.insert(3,3)
        cusLinkedList.insert(4,4)
        cusLinkedList.insert(5,5)
        cusLinkedList.insert(6,6)
        cusLinkedList.insert(7,7)
        cusLinkedList.insert(8,8)

        tv_link_content.text = cusLinkedList.outPut()
    }

    private fun delArray() {
        val cusArray = CustomArray(10)
        cusArray.insert(0, 0)
        cusArray.insert(1, 1)
        cusArray.insert(2, 2)
        cusArray.insert(3, 3)
        cusArray.insert(4, 4)
        cusArray.insert(5, 5)
        cusArray.insert(6, 6)
        cusArray.insert(7, 7)
        cusArray.insert(8, 8)
        cusArray.insert(9, 9)
        cusArray.insert(10, 10)

        val delete = cusArray.delete(5)
        tv_array_content.text = "删除数据:$delete"
    }

    private fun addArray() {
        val cusArray = CustomArray(10)
        cusArray.insert(0, 0)
        cusArray.insert(1, 1)
        cusArray.insert(2, 2)
        cusArray.insert(3, 3)
        cusArray.insert(4, 4)
        cusArray.insert(5, 5)
        cusArray.insert(6, 6)
        cusArray.insert(7, 7)
        cusArray.insert(8, 8)
        cusArray.insert(9, 9)
        cusArray.insert(10, 10)
        /*for (i in 0 until cusArray.size) {

        }*/
        //cusArray.set(0, 10)
        val builder = StringBuilder()
        repeat(cusArray.size) {
            builder.append(cusArray[it]).append(" ")
        }
        tv_array_content.text = builder.toString()
    }
}