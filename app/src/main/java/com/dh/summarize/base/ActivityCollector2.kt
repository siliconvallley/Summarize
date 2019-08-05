package com.dh.summarize.base

import android.app.Activity
import java.lang.ref.WeakReference
import java.util.*

/**
 * @author : 86351
 * @date : 2018/12/20
 * @description : ActivityCollector2
 * @version :
 */
object ActivityCollector2{

    private var activities:Stack<WeakReference<Activity>> = Stack()

    fun pushTask(task:WeakReference<Activity>){
        activities.push(task)
    }

    fun popTask(task:WeakReference<Activity>){
        activities.remove(task)
    }

    fun popTaskAll(){
        for (task in activities){
            if (!task.get()!!.isFinishing){
                task.get()!!.finish()
            }
        }
        //杀死应用的进程
        //android.os.Process.killProcess(android.os.Process.myPid())
    }
}
