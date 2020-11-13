package com.zdww.jssl_test.musicsList

import com.zdww.jssl_test.lifecycle.AbsLifecycle
import com.zdww.jssl_test.lifecycle.ILifecycleOwner
import com.zdww.jssl_test.lifecycle.LifeState
import com.zdww.jssl_test.musicsList_jetpack.MusicModelJet
import com.zdww.jssl_test.player.DataListenContainer
import com.zdww.jssl_test.player.domain.Music

/**
 * @desc
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2020/11/12 23:57
 */
class MusicPresenter(owner: ILifecycleOwner){
    enum class MusicLoadState {
        LOADING,EMPTY,SUCCESS,ERROR
    }
    private val musicModel by lazy { MusicModelJet() }

    val musicList = DataListenContainer<List<Music>>()

    val loadState = DataListenContainer<MusicLoadState>()
    private val page = 1;
    private val size = 30;

    init {
        owner.getLifecycleOwner().addLifecycleListener(ViewLifeImpl())
    }
    //获取音乐
    fun getMusic() {
        loadState.value = MusicLoadState.LOADING;
        //从model层获取音乐数据
        musicModel.loadMusicByPage(page, size, object : MusicModelJet.onMusicLoadResult {
            override fun onSuccess(result: List<Music>) {
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

    inner class ViewLifeImpl: AbsLifecycle() {

        override fun onCreate() {

        }

        override fun onStart() {
            println("监听GPS信号的变化")
            //开始监听网络变化
        }

        override fun onStop() {
            println("停止GPS信号的变化")
            //停止监听网络状态变化更新
        }

        override fun onViewLifeStateChange(state: LifeState) {
            println("currentstate ==> $state")
        }
    }
}