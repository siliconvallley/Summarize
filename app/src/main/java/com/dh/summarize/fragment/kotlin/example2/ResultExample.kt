package com.dh.summarize.fragment.kotlin.example2

/**
 * @author 86351
 * @date 2019/10/2
 * @description
 */


fun main() {
    val user = User(1, "张三", PlayerViewType.VIP(null,null))
    PlayerUI.getInstance().showPlayer(user)
}


sealed class PlayerViewType {
    object GREEN : PlayerViewType()
    object YELLOW : PlayerViewType()
    class VIP(val title: String?, val content: String?) : PlayerViewType()
}

fun getPlayViewType(type: PlayerViewType): PlayerView = when (type) {
    PlayerViewType.GREEN -> GreenPlayerView()
    PlayerViewType.YELLOW -> YellowPlayerView()
    is PlayerViewType.VIP -> VipPlayerView(type.title, type.content)
}


interface PlayerView {
    fun showView()
}

/**
 * 黄色播放器
 */
class YellowPlayerView : PlayerView {
    override fun showView() {
        println("黄色播放器")
    }
}

/**
 * 黄色播放器
 */
class GreenPlayerView : PlayerView {
    override fun showView() {
        println("绿色播放器")
    }
}

// 动态代理
class MediaPlayerView(playerView: PlayerView) : PlayerView by playerView


private const val defaultTitle = "VIP用户"
private const val defaultContent = "欢迎VIP用户"

class VipPlayerView(var title: String?, var content: String?) : PlayerView {

    // 需要在构造方法中完成的操作，可以放在init代码块中完成
    init {
        // 表示如果title不为空返回title本身的内容，为空就返回默认title
        title = title ?: defaultTitle
        content = content ?: defaultContent
    }

    override fun showView() {
        println("标题:$title  ==>  内容:$content")
    }

}