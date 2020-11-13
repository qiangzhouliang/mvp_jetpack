package com.zdww.jssl_test.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.zdww.jssl_test.view.IBaseView
import java.lang.ref.WeakReference

/**
 * @desc 中间业务基础类
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2020/11/10 16:58
 * @class jssl-test
 * @package com.zdww.jssl_test.presenter
 */
open class BasePresent<T :IBaseView> : LifecycleObserver {
    // IBaseView
    var iGoodsView: WeakReference<T>? = null

    //绑定
    fun attachView(view:T){
        iGoodsView = WeakReference<T>(view)
    }

    //解绑
    fun deatchView(){
        iGoodsView?.clear()
        iGoodsView = null
    }

    //jetpack 监听 activity生命周期 方法
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate(owner: LifecycleOwner){}
    
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart(owner: LifecycleOwner){}

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPush(owner: LifecycleOwner){}

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop(owner: LifecycleOwner){}

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume(owner: LifecycleOwner){}

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestory(owner: LifecycleOwner){}


}