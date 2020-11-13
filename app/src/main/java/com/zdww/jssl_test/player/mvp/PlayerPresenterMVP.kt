package com.zdww.jssl_test.player.mvp

import com.zdww.jssl_test.player.IPlayerCallback
import com.zdww.jssl_test.player.MusicPlayer
import com.zdww.jssl_test.player.PlayerModel
import com.zdww.jssl_test.player.domain.Music

/**
 * @desc
 * @anthor qiangzhouliang
 * 播放音乐
 * 暂停音乐
 * 上一首
 * 下一首
 * ===============
 * 播放的状态
 * - 通知UI改变成播放的状态
 * - 通知UI歌曲标题变化
 * 上一首，下一首
 * - 通知UI歌曲标题变化
 * - 通知UI歌曲封面变化
 * 暂停音乐
 * - 更新UI状态为暂停
 *
 * 相关数据：
 * 当前播放的歌曲
 * 当前播放的状态
 */
class PlayerPresenterMVP private constructor(){

    private val playerModel by lazy { PlayerModel() }
    private val player by lazy { MusicPlayer() }

    //当前音乐
    private var currentMusic:Music? = null
    //当前播放状态
    private var currentPlayState = PlayState.NONE;

    //单例
    companion object {
        val instance by lazy { PlayerPresenterMVP() }
    }

    enum class PlayState {
        NONE,PLAYING,PAUSE,LOADING
    }
    private val callbacksList = arrayListOf<IPlayerCallback>()


    //注册通知
    fun registerCallback(callback: IPlayerCallback) {
        if (!callbacksList.contains(callback)){
            callbacksList.add(callback)
        }
    }

    fun unRegisterCallback(callback: IPlayerCallback) {
        callbacksList.remove(callback)
    }

    /**
     * 根据状态控制播放暂停
     */
    fun doPlayOrPause() {
        if (currentMusic == null){
            //去拿一首歌曲
            currentMusic = playerModel.getMusicById("xxx")
        }

        //播放音乐
        player.play(currentMusic)

        dispatchTitleChange("当前播放的歌曲标题。。。")
        dispatchCoverChange("当前播放的歌曲封面。。。")
        currentPlayState = if (currentPlayState != PlayState.PLAYING){
            //开始播放
            dispatchPlayingState()
            PlayState.PLAYING
        } else {
            //暂停播放
            dispatchPlayPauseState()
            PlayState.PAUSE
        }
    }

    //通知暂停
    private fun dispatchPlayPauseState() {
        callbacksList.forEach(){
            it.onPlayPause()
        }
    }

    //通知播放
    private fun dispatchPlayingState() {
        callbacksList.forEach(){
            it.onPlaying()
        }
    }

    /**
     * 播放下一首
     */
    fun playNext() {
        //播放下一首内容
        // 1 拿到下一首歌曲 -》 变更UI，包括标题和封面
        dispatchTitleChange("切换到下一首，标题变化了。。。")
        dispatchCoverChange("切换到下一首，封面变化了。。。")
        // 2 设置给播放器
        // 3 等待播放器的回调
        currentPlayState = PlayState.PLAYING
    }

    private fun dispatchCoverChange(cover: String) {
        callbacksList.forEach {
            it.onCoverChange(cover)
        }
    }

    private fun dispatchTitleChange(title: String) {
        callbacksList.forEach {
            it.onTitleChange(title)
        }
    }

    /**
     * 播放上一首
     */
    fun playPre() {
        dispatchTitleChange("切换到上一首，标题变化了。。。")
        dispatchCoverChange("切换到上一首，封面变化了。。。")

        currentPlayState = PlayState.PLAYING
    }
}