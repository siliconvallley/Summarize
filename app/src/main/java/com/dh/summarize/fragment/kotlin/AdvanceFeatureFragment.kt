package com.dh.summarize.fragment.kotlin

import android.annotation.SuppressLint
import android.view.View
import com.dh.summarize.R
import com.dh.summarize.base.BaseFragment
import com.dh.summarize.fragment.kotlin.entity.OperatorBean
import com.dh.summarize.fragment.kotlin.entity.UserBean
import com.dh.summarize.fragment.kotlin.example.name
import com.dh.utils_library.utils.LogUtils
import kotlinx.android.synthetic.main.advance_feature_fragment_layout.*

/**
 * 1、解构：遍历map的时候比较常用
 *  for ((key, value) in map) {
 *      println("键:$key==>值:$value")
 *  }
 * 2、循环与集合操作符
 * 3、运算符重载
 * 4、作用域函数
 * 5、中缀表达式
 * 6、DSL
 */
class AdvanceFeatureFragment : BaseFragment(), View.OnClickListener {

    companion object {
        fun getInstance() = AdvanceFeatureFragment()
        private const val TAG = "AdvanceFeatureFragment"
    }

    override fun getLayoutId(): Int {
        return R.layout.advance_feature_fragment_layout
    }

    override fun initViews(view: View) {
    }

    override fun initListener() {
        bt_oprator.setOnClickListener(this)
        bt_list.setOnClickListener(this)
        bt_overload.setOnClickListener(this)
        bt_scope.setOnClickListener(this)
        bt_infix.setOnClickListener(this)
        bt_dsl.setOnClickListener(this)
        bt_list_ope.setOnClickListener(this)
    }

    override fun initData() {
        //opeM()
    }

    private fun opeM() {
        val list = arrayListOf(0, 1)
        // 常用的集合操作符：
        // 元素操作类
        list.contains(0) // 判断是否有指定元素
        list.elementAt(0) // 返回对应的元素
        list.firstOrNull {
            it > 0
        } // 返回符合条件的第一个元素，没有则返回null
        list.lastOrNull {
            it > 0
        } // 返回符合条件的最后一个元素，没有则返回null
        list.indexOf(1) // 返回指定元素的下标
        list.singleOrNull {
            it != 0
        } // 返回符合条件的单个元素，没有或者超过一个，返回null

        // 判断类
        list.any {
            it != 0
        } // 判断集合中是否有满足条件的元素
        list.all {
            it > 0
        } // 判断集合中的元素是否都满足条件
        list.none {
            it < 0
        } // 判断结合中的元素是否都不满足条件
        list.count {
            it > 0
        } // 判断集合中满足条件元素的个数
        list.reduce { acc, i ->
            acc + i
        } // 从第一项到最后一项进行累计

        // 过滤类
        list.filter {
            it > 0
        } // 过滤掉满足条件的所有元素
        list.filterNot {
            it > 0
        } // 过滤掉不满足条件的所有元素
        list.filterNotNull() // 过滤Null
        list.take(2) // 返回前n个元素

        // 转换类
        list.map {
            it.toString()
        } // 将集合转换成另一个集合
        // list.flatMap {} // 自定义逻辑合并两个集合
        list.groupBy {
            it > 0
        } // 按照某个条件进行分组，返回map

        // 排序类
        list.reversed() // 反序
        list.sorted() // 升序排序
        // list.sortedBy {  } // 自定义排序
        list.sortedDescending() //降序
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_oprator -> {
                val operatorBean = OperatorBean("张三", 20)
                // 解构，其实这里kotlin具体操作，具体可以看编译后的JAVA文件
                // name = operatorBean.component1()
                // age = operatorBean.component2()
                val (name, age) = operatorBean
                tv_content.text = "姓名:$name===>年龄:$age"
            }
            R.id.bt_list -> {
                list1()
            }
            R.id.bt_list_ope -> {
                listOpe()
            }
            R.id.bt_overload -> {

            }
            R.id.bt_scope -> {
                scopeM()
            }
            R.id.bt_infix -> {
                infixM()
            }
            R.id.bt_dsl -> {
                // 官方有个例子可以去参考
            }
        }
    }


    fun `2`() {
        val `object` = "kotlin可以通过``实现使用kotlin关键字的操作"
    }

    /**
     * 中缀表达式
     */
    private fun infixM() {
        // step就是一个中缀表达式，中缀表达式需要用infix修饰
        for (i in 0..10 step 2) {

        }

        //tv_content.text = 5.vs(5).toString()
        tv_content.text = (5 vs 6).toString()
    }


    /**
     * 中缀表达式
     */
    private infix fun Int.vs(num: Int): CompareResult {
        return when {
            this - num > 0 -> CompareResult.MORE
            this - num < 0 -> CompareResult.LESS
            else -> CompareResult.EQUAL
        }
    }

    sealed class CompareResult {
        object LESS : CompareResult() {
            override fun toString(): String {
                return "小于"
            }
        }

        object MORE : CompareResult() {
            override fun toString(): String {
                return "大于"
            }
        }

        object EQUAL : CompareResult() {
            override fun toString(): String {
                return "等于"
            }
        }
    }

    /**
     * 作用域函数
     */
    private fun scopeM() {
        val builder = StringBuilder()
        // let和run都会返回lambda闭包的执行结果，区别在于let有闭包参数，run没有闭包参数
        val user = UserBean("张三")
        // let有闭包参数，比如user调用了let，闭包参数就是user
        val letName = user.let { userBean ->
            "let:${userBean.name}"
        }
        // 而run是没有闭包参数，比如user调用了run，this就是用来指代调用者user
        val runName = user.run {
            "run:${this.name}"
        }
        builder.append(letName).append("==>$runName\n")

        // also和apply都不会返回闭包的执行结果(返回的是本身的调用对象)，区别在于also有闭包参数，apply没有闭包参数
        /*user.also {
            builder.append("also:${it.name}")
        }
        user.apply {
            builder.append("==>apply:${this.name}\n")
        }*/
        user.also {
            builder.append("also:${it.name}")
        }.apply {
            builder.append("==>apply:${this.name}\n")
        }.name = "李斯"

        user.also {
            builder.append("also:${it.name}")
        }.apply {
            builder.append("==>apply:${this.name}\n")
        }

        // takeIf 返回一个判断结果，为false时，takeIf会返回空
        // takeUnless跟takeIf刚好相反，闭包的返回结果为true时，会返回空
        // 所以我们在使用的时候会使用?.去调用takeIf和takeUnless后面的函数
        // 不用?.这种方式调用，你会发现also中的方法和?:后的方法都会走，原理很简单?.调用是条件成立才执行
        user.takeIf {
            it.name.isNotEmpty() && it.name.isNotBlank()
        }?.also {
            builder.append("takeIf:${it.name}")
        } ?: builder.append("takeIf:姓名为空")

        user.takeUnless {
            it.name.isNotEmpty() && it.name.isNotBlank()
        }?.also {
            builder.append("takeUnless:姓名为空")
        } ?: builder.append("takeUnless:${user.name}")

        // with比较特殊，它不是以扩展函数存在的，而是一个顶级函数
        // Android中可以使用with对各个View赋值
        with(user) {
            //user.name = "王五"
            this.name = "张三"
        }
        tv_content.text = builder.toString()
    }

    /**
     * 集合操作符，跟RxJava类似
     */
    private fun listOpe() {
        val builder = StringBuilder()
        val list = arrayListOf('a', 'b', 'c', 'd', 'e')
        // map操作符就是在其中做相应的操作，将数据转变为我们想要的结果
        val findValue = list.map {
            it - 'a'
        }.filter {
            it > 0
        }.find {
            // find操作符返回的是符合这个lambda闭包操作的第一个值
            // findLast操作符是返回符合lambda闭包操作的最后一个值
            it > 1
        }
        builder.append("测试简单操作符:$findValue\n")

        val strList = arrayListOf("4", "0", "7", "i", "f", "w", "0", "9")
        val indexList = arrayListOf(5, 3, 9, 4, 8, 3, 1, 9, 2, 1, 7)
        indexList.filter {
            it < strList.size
        }.map {
            strList[it]
        }.reduce { acc, s ->
            // 组合
            "$acc$s"
        }.also {
            builder.append("拼装结果:$it\n")
        }

        val cusList = arrayListOf("a", "b", "c", "d")
        cusList.convert {
            "2$it"
        }.also {
            builder.append("自定义操作符转换结果:")
            for (result in it) {
                builder.append(result).append(" ")
            }
        }

        tv_content.text = builder.toString()
    }

    /**
     * 自定义操作符，去扩展的Iterable，将集合转变为另一个集合
     */
    private inline fun <T, R> Iterable<T>.convert(action: (T) -> R): Iterable<R> {
        //private inline fun <T, R> Iterable<T>.convert(action: (T) -> R): MutableList<R> {
        val list: MutableList<R> = arrayListOf()
        for (item: T in this) {
            list.add(action(item))
        }
        return list
    }

    /**
     * 循环、遍历
     */
    private fun list1() {
        val builder = StringBuilder()
        builder.append("for (i in 0..10)会输出0-10：")
        for (i in 0..10) {
            builder.append("$i").append(" ")
        }

        builder.append("\nfor (i in 0 until 10)会输出0-9：")
        for (i in 0 until 10) {
            builder.append("$i").append(" ")
        }

        builder.append("\nfor (i in 10 downTo 0)会输出10-0：")
        for (i in 10 downTo 0) {
            builder.append("$i").append(" ")
        }

        builder.append("\nfor (i in 0..10 step 2)走两步正序：")
        for (i in 0..10 step 2) {
            builder.append("$i").append(" ")
        }

        builder.append("\nfor (i in 10 downTo 0 step 2)走两步倒序：")
        for (i in 10 downTo 0 step 2) {
            builder.append("$i").append(" ")
        }

        builder.append("\nrepeat(times,action:(Int) -> Unit)会输出0-9，查看源码可以知道就是实现的for (i in 0 until 10):")
        /*val action: (Int) -> Unit
        action = testOutput::output*/
        //repeat(10, testOutput::output)
        repeat(10) {
            builder.append("$it").append(" ")
        }
        /*traverList(10) {
            builder.append("$it").append(" ")
        }*/

        builder.append("\nfor (num in testList)遍历集合:")
        val testList = arrayListOf<String>("a", "b", "c", "d")
        for (str in testList) {
            builder.append(str).append(" ")
        }

        builder.append("\nfor ((index, value) in testList.withIndex())遍历集合会同时输出下标和数值，其实就是解构:")
        for ((index, value) in testList.withIndex()) {
            builder.append("下标:$index").append("==>值:$value").append(" ")
        }
        tv_content.text = builder.toString()

        val list: MutableList<OperatorBean> = arrayListOf()
        list.add(OperatorBean("张三", 20))
        list.add(OperatorBean("李斯", 40))
        for ((name, age) in list) {

        }
        for ((operatorBean, i) in list.withIndex()) {

        }
        for (operatorBean in list) {

        }
        for (i in list.indices) {

        }
        list.forEach {

        }
    }

    private interface TestOutput {
        fun output(num: Int)
    }

    private val testOutput = object : TestOutput {
        override fun output(num: Int) {
            LogUtils.d(TAG, "输出参数:$num")
        }
    }

    // 高阶函数声明中用inline关键字，在编译的时候回拆解函数的调用为语句的调用
    // traverList(10,testOutput::output)，高阶函数具体的调用，调用的是方法的引用，对象名::方法名
    // traverList(10) {} lambda的调用方式，是因为最后一个参数是一个lambda表达式，所以可以写成这样
    private inline fun traverList(times: Int, action: (Int) -> Unit) {
        for (index in 0 until times) {
            action(index)
        }
    }

}