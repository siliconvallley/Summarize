package com.dh.summarize.fragment.answer

import android.view.View
import com.dh.summarize.R
import com.dh.summarize.base.BaseFragment
import kotlinx.android.synthetic.main.iterator_fragment_layout.*
import java.util.*

/**
 * ArrayList 	数组结构 	可以根据下标直接取值。
 * LinkedList 	链表结构 	如果需要寻找某一个下标的数值必须从头遍历。
 * ArrayList与LinkedList遍历性能比较
 * https://github.com/GcsSloop/AndroidNote/blob/magic-world/ChaosCrystal/List%E9%81%8D%E5%8E%86%E6%80%A7%E8%83%BD.md
 */
class IteratorListFragment : BaseFragment() {
    companion object {
        fun getInstance(): IteratorListFragment {
            return IteratorListFragment()
        }
        private val EXPLANATION_STR =
            "经过测试：\n" +
            "常规的for循环（就是将下标遍历出来取值）：ArrayList效率其实影响不大，但是LinkedList就会相当的慢、时间开销相当大\n" +
            "迭代器Iterator和ForEach情况下：ArrayList和LinkedList遍历效率都很高，ForEach就是实现的迭代器，所以没什么区别\n" +
            "所以建议对List集合进行遍历的时候，尽量时候后两种方式"
    }

    private lateinit var list: MutableList<Int>
    private lateinit var linkList: MutableList<Int>

    override fun getLayoutId(): Int {
        return R.layout.iterator_fragment_layout
    }

    override fun initViews(view: View) {
    }

    override fun initListener() {
        bt_common.setOnClickListener(onClickListener)
        bt_iterator.setOnClickListener(onClickListener)
        bt_each.setOnClickListener(onClickListener)
    }

    override fun initData() {
        list = arrayListOf()
        linkList = LinkedList()
        for (i in 0..50000) {
            list.add(i)
            linkList.add(i)
        }
    }

    private val onClickListener = View.OnClickListener {
        when (it.id) {
            R.id.bt_common -> {
                listCommon()
            }
            R.id.bt_iterator -> {
                listIterator()
            }
            R.id.bt_each -> {
                listForEach()
            }
        }
    }

    private fun listCommon() {
        val builder = StringBuilder()
        val listStart = System.currentTimeMillis()
        for (i in 0 until list.size) {
            val value = list[i]
        }
        val listEnd = System.currentTimeMillis()
        builder.append("For_ArrayList-->${listEnd - listStart}\n")

        val linkStart = System.currentTimeMillis()
        for (i in 0 until linkList.size) {
            val value = linkList[i]
        }
        val linkEnd = System.currentTimeMillis()
        builder.append("For_LinkedList-->${linkEnd - linkStart}\n")
        builder.append(EXPLANATION_STR)
        tv_content.text = builder.toString()
    }

    private fun listIterator() {
        val builder = StringBuilder()
        val listStart = System.currentTimeMillis()
        val iteratorList = list.iterator()
        while (iteratorList.hasNext()) {
            val value = iteratorList.next()
        }
        val listEnd = System.currentTimeMillis()
        builder.append("For_ArrayList-->${listEnd - listStart}\n")

        val linkStart = System.currentTimeMillis()
        val iteratorLink = linkList.iterator()
        while (iteratorLink.hasNext()) {
            val value = iteratorLink.next()
        }
        val linkEnd = System.currentTimeMillis()
        builder.append("For_LinkedList-->${linkEnd - linkStart}\n")
        builder.append(EXPLANATION_STR)
        tv_content.text = builder.toString()
    }

    private fun listForEach() {
        val builder = StringBuilder()
        val listStart = System.currentTimeMillis()
        for (value in list) {

        }
        val listEnd = System.currentTimeMillis()
        builder.append("For_ArrayList-->${listEnd - listStart}\n")

        val linkStart = System.currentTimeMillis()
        for (value in linkList) {

        }
        val linkEnd = System.currentTimeMillis()
        builder.append("For_LinkedList-->${linkEnd - linkStart}\n")
        builder.append(EXPLANATION_STR)
        tv_content.text = builder.toString()
    }
}