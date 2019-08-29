package com.dh.utils_library.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlin.jvm.functions.FunctionN

object FragmentUtils {

    @JvmStatic
    fun replaceFragment(fm: FragmentManager, fragment: Fragment, containerId: Int) {
        fm.beginTransaction()
            .replace(containerId, fragment, fragment::class.java.simpleName)
            .commit()
    }
}