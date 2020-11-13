package com.zdww.jssl_test.player

/**
 * @desc
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 */
interface IPlayerCallback {
    fun onTitleChange(title: String)

    fun onProgressChange(current: Int)

    fun onPlaying()

    fun onPlayPause()

    fun onCoverChange(cover: String)
}