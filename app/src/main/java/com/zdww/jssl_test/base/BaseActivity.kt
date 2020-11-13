package com.zdww.jssl_test.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zdww.jssl_test.lifecycle.ILifecycleOwner
import com.zdww.jssl_test.lifecycle.LifeState
import com.zdww.jssl_test.lifecycle.LifecycleProvider

/**
 * @desc 基础activity
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 */
open class BaseActivity: AppCompatActivity(), ILifecycleOwner {

    protected val lifeProvider by lazy { LifecycleProvider() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifeProvider.makeLifeState(LifeState.CREATE)
    }

    override fun onStart() {
        super.onStart()
        lifeProvider.makeLifeState(LifeState.START)
    }

    override fun onResume() {
        super.onResume()
        lifeProvider.makeLifeState(LifeState.RESUME)
    }

    override fun onPause() {
        super.onPause()
        lifeProvider.makeLifeState(LifeState.PAUSE)
    }

    override fun onStop() {
        super.onStop()
        lifeProvider.makeLifeState(LifeState.STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifeProvider.makeLifeState(LifeState.DESTROY)
    }

    override fun getLifecycleOwner(): LifecycleProvider {
        return lifeProvider
    }
}