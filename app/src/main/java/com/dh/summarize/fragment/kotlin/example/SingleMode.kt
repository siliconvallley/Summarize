package com.dh.summarize.fragment.kotlin.example

/**
 * 单例模式
 */
class SingleMode private constructor() {
    // 利用伴生对象实现单例模式，其实这种写法跟JAVA的静态内部类实现单例模式类似
    private object SingleHolder {
        val instance = SingleMode()
    }

    companion object {

        fun getInstance(): SingleMode {
            return SingleHolder.instance
        }

        // 简写
        //fun getInstance(): SingleMode = SingleHolder.instance

        //val singleMode: SingleMode by lazy { SingleHolder.instance}

        // JAVA的双重判断
        /*private var instance: SingleMode? = null
        fun getInstance(): SingleMode {
            if (instance == null) {
                synchronized(SingleMode::class) {
                    if (instance == null) {
                        instance = SingleMode()
                    }
                }
            }
            return instance!!
        }*/

        /*private var instance: SingleMode? = null
        val INSTANCE: SingleMode
            get() {
                if (instance == null) {
                    synchronized(SingleMode::class) {
                        if (instance == null) {
                            instance = SingleMode()
                        }
                    }
                }
                return instance!!
            }*/
    }

}