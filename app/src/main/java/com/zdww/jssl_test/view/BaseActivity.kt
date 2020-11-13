package com.zdww.jssl_test.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zdww.jssl_test.R
import com.zdww.jssl_test.presenter.BasePresent

abstract class BaseActivity<V:IBaseView,T: BasePresent<V>> : AppCompatActivity() {
    protected lateinit var presenter:T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //选择自己的表示层
        presenter = createPresenter()
        presenter.attachView(this as V)
        registerSDK()
        initAct()
    }

    protected open fun initAct(){}

    protected open fun registerSDK() {}
    protected open fun unRegisterSDK() {}
    protected abstract fun createPresenter(): T

    override fun onDestroy() {
        super.onDestroy()
        presenter.deatchView()
        unRegisterSDK()
    }

}