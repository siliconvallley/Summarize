package com.dh.summarize.fragment.kotlin.example2

/**
 * @author 86351
 * @date 2019/10/2
 * @description
 * val type: PlayerViewType = PlayerViewType.YELLOW  我们可以给参数设置一个默认值
 */
data class User(val id: Int, val name: String, val type: PlayerViewType = PlayerViewType.YELLOW)