package com.zdww.jssl_test.musicsList_jetpack

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.zdww.jssl_test.base.DataListenContainerJet
import com.zdww.jssl_test.player.domain.Music

/**
 * @desc lifecycle的使用
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2020/11/12 23:57
 */
class MusicPresenterJet(owner: LifecycleOwner){
    enum class MusicLoadState {
        LOADING,EMPTY,SUCCESS,ERROR
    }
    private val musicModel by lazy { MusicModelJet() }

    val liveMusicList = MutableLiveData<List<Music>>()
    val liveLoadState = MutableLiveData<MusicLoadState>()


    val musicList = DataListenContainerJet<List<Music>>()
    val loadState = DataListenContainerJet<MusicLoadState>()

    private val page = 1
    private val size = 30

    init {
        owner.lifecycle.addObserver(ViewLifeImpl())
    }
    //获取音乐
    fun getMusic() {
        loadState.value = MusicLoadState.LOADING;
        //从model层获取音乐数据
        musicModel.loadMusicByPage(page, size, object : MusicModelJet.onMusicLoadResult {
            override fun onSuccess(result: List<Music>) {
                liveMusicList.postValue(result)
                if (result.isEmpty()) {
                    liveLoadState.postValue(MusicLoadState.EMPTY)
                } else {
                    liveLoadState.postValue(MusicLoadState.SUCCESS)
                }

                musicList.value = result
                loadState.value = if (result.isEmpty()) {
                    MusicLoadState.EMPTY
                } else {
                    MusicLoadState.SUCCESS
                }
            }

            override fun onError(msg: String, code: Int) {
                loadState.value = MusicLoadState.ERROR
                println("error ... $msg ... $code")
            }

        })
    }

    inner class ViewLifeImpl: LifecycleEventObserver {

        /**
         * 被动通知
         */
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            println("currentstate ==> $event")
            when(event){
                Lifecycle.Event.ON_START -> {
                    println("监听GPS信号变化")
                }
                Lifecycle.Event.ON_PAUSE -> {
                    println("停止监听GPS信号变化")
                }
                else -> {}
            }
        }
    }
}