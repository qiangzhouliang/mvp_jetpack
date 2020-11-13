package com.zdww.jssl_test.musicsList

import com.zdww.jssl_test.player.domain.Music

/**
 * @desc
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2020/11/12 23:57
 */
class MusicModel {
    interface onMusicLoadResult {

        fun onSuccess(result: List<Music>)

        fun onError(msg: String, code: Int)

    }
    //获取音乐数据
    fun loadMusicByPage(page: Int, size: Int,callback:onMusicLoadResult) {
        val result = arrayListOf<Music>()
        Thread{
            for (i in 0 until size){
                result.add(Music("音乐名称：$i", "封面： $i", "url ==> $i"))
            }
            //数据请求完成

            //通知数据更新
            callback.onSuccess(result)
        }.start()
    }
}