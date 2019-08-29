package com.dh.summarize.fragment.kotlin.entity

import java.util.*

class UserBean1(var birthDay: Int) {
    val age: Int
        get() {
            return Calendar.getInstance().get(Calendar.YEAR) - birthDay
        }

    fun oneYearLater() {
        birthDay--
    }
}