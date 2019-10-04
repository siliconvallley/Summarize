package com.dh.summarize.fragment.kotlin.view

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * @author 86351
 * @date 2019/9/29
 * @description
 */
class TestView : View {
    /*private
    public
    protected
    internal */

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

    }
}
