package com.zdww.jssl_test.musicsList

import android.os.Bundle
import com.zdww.jssl_test.R
import com.zdww.jssl_test.base.BaseActivity
import com.zdww.jssl_test.musicsList_jetpack.MusicPresenterJet
import kotlinx.android.synthetic.main.activity_musics.*

/**
 * Activity 生命周期
 * onCreate  创建 -- 还不可见
 * onstart   可见 -- 不可以交换
 * onResume  可见 -- 可已交互
 * onPause   可见 -- 不可交互
 * onStop    不可见 -- 不可交互
 * onDestory 销毁  -- 不可见
 *
 * 启动Activity
 * onCreate -- onstart -- onResume 可见可交互
 * 按返回键（如果没有处理返回键，就是退出）
 * onPause -- onStop -- onDestory
 * ============================================
 * 启动Activity
 * onCreate -- onstart -- onResume 可见可交互
 * 按Home键
 * onPause -- onStop
 * ============================================
 *  启动Activity
 * onCreate -- onstart -- onResume 可见可交互
 * 启动另一个Activity
 * onPause -- onStop
 * ===============================================
 * 启动透明的Activity，或者弹出 Dialog
 * onCreate -- onstart -- onResume 可见可交互
 * 启动另一个Activity
 * onPause
 *
 */
class MusicsActivity : BaseActivity() {
    private val musicPresenter by lazy { MusicPresenterJet(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musics)
        initDataListener()
        initViewListener()
    }

    //监听数据变化
    private fun initDataListener() {
        musicPresenter.musicList.addListener(this) {
            println(Thread.currentThread().name)
            //数据变化
            println("加载结果。。。${it?.size}")
        }

        musicPresenter.loadState.addListener(this) {
            //状态变化
            println("加载状态。。。$it")
        }
    }

    private fun initViewListener() {
        getMusicList.setOnClickListener {
            musicPresenter.getMusic()
        }
    }
}