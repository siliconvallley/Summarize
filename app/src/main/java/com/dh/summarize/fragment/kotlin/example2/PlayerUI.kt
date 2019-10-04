package com.dh.summarize.fragment.kotlin.example2

/**
 * @author 86351
 * @date 2019/10/2
 * @description
 */
class PlayerUI {

    companion object {
        fun getInstance() = PlayerHolder.palyerUI
    }

    private object PlayerHolder {
        val palyerUI = PlayerUI()
    }

    fun showPlayer(user: User) {
        MediaPlayerView(getPlayViewType(user.type)).showView()
    }
}