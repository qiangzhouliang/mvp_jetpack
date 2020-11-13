package com.zdww.jssl_test.player

import android.os.Bundle
import com.zdww.jssl_test.R
import com.zdww.jssl_test.base.BaseActivity
import kotlinx.android.synthetic.main.activity_flow_player_controller.playOrPauseBtn

class FlowPlayerControllerActivity : BaseActivity() {
    private val playerPresenter by lazy { PlayerPresenter.instance }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_player_controller)
        initListener()
        initDataListener()
    }

    private fun initListener() {
        playOrPauseBtn.setOnClickListener {
            playerPresenter.doPlayOrPause()
        }
    }

    /**
     * 监听数据的变化
     */
    private fun initDataListener() {
        playerPresenter.currentPlayState.addListener(this) {
            when(it){
                PlayerPresenter.PlayState.PAUSE -> {
                    //播放中 ---》 显示暂停
                    playOrPauseBtn.text = "播放"
                }
                PlayerPresenter.PlayState.PLAYING -> {
                    //播放中 ---》 显示暂停
                    playOrPauseBtn.text = "暂停"
                }
            }
        }
    }
}