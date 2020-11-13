package com.zdww.jssl_test.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zdww.jssl_test.R
import com.zdww.jssl_test.base.BaseActivity
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : BaseActivity() {
    private val playerPresenter by lazy { PlayerPresenter.instance }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        initDataListener()
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
    /**
     * 监听数据的变化
     */
    private fun initDataListener() {
        playerPresenter.currentMusic.addListener(this) {
            //音乐内容发生变化，更新UI
            song_title.text = it?.name
            println("封面改变了。。。${it?.cover}")
        }
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