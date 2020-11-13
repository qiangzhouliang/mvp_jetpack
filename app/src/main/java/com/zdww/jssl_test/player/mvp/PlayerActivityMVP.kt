package com.zdww.jssl_test.player.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zdww.jssl_test.R
import com.zdww.jssl_test.player.IPlayerCallback
import com.zdww.jssl_test.player.PlayerPresenter
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivityMVP : AppCompatActivity(), IPlayerCallback {
    private val playerPresenter by lazy { PlayerPresenterMVP.instance }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        playerPresenter.registerCallback(this)
        initListener()
    }


    //给控件设置点击事件
    private fun initListener() {
        playOrPauseBtn.setOnClickListener {
            //调运presenter层的播放或者暂停方法
            playerPresenter.doPlayOrPause()
        }

        playNext.setOnClickListener {
            playerPresenter.playNext()
        }
        playPreBtn.setOnClickListener {
            playerPresenter.playPre()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        playerPresenter.unRegisterCallback(this)
    }

    override fun onTitleChange(title: String) {
        song_title?.text = title
    }

    override fun onProgressChange(current: Int) {
    }

    override fun onPlaying() {
        //播放中 ---》 显示暂停
        playOrPauseBtn.text = "暂停"
    }

    override fun onPlayPause() {
        //暂停中 ---》 显示播放
        playOrPauseBtn.text = "播放"
    }

    override fun onCoverChange(cover: String) {
        println("封面改变了:  $cover")
    }
}