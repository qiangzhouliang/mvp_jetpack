package com.zdww.jssl_test.player

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
class PlayerPresenter private constructor(){

    private val playerModel by lazy { PlayerModel() }
    private val player by lazy { MusicPlayer() }

    //当前音乐 - 这样设置数据就会被监听
    var currentMusic = DataListenContainer<Music>()
    //当前播放状态 - 这样设置数据就会被监听
    var currentPlayState = DataListenContainer<PlayState>()

    //单例
    companion object {
        val instance by lazy { PlayerPresenter() }
    }

    enum class PlayState {
        NONE,PLAYING,PAUSE,LOADING
    }



    /**
     * 根据状态控制播放暂停
     */
    fun doPlayOrPause() {
        //去拿一首歌曲
        currentMusic.value = playerModel.getMusicById("卡农")

        //播放音乐
        player.play(currentMusic.value)

        currentPlayState.value = if (currentPlayState.value != PlayState.PLAYING){
            //开始播放
            PlayState.PLAYING
        } else {
            //暂停播放
            PlayState.PAUSE
        }
    }


    /**
     * 播放下一首
     */
    fun playNext() {
        //赋值下一首音乐
        currentMusic.value = playerModel.getMusicById("下一首：梦中的婚礼")
        //播放下一首内容
        // 1 拿到下一首歌曲 -》 变更UI，包括标题和封面
        // 2 设置给播放器
        // 3 等待播放器的回调
        currentPlayState.value = PlayState.PLAYING
    }


    /**
     * 播放上一首
     */
    fun playPre() {
        //赋值上一首音乐
        currentMusic.value = playerModel.getMusicById("上一首：水中月亮")

        currentPlayState.value = PlayState.PLAYING
    }
}