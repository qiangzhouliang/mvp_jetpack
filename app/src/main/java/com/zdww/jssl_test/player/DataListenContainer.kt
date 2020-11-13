package com.zdww.jssl_test.player

import android.os.Looper
import com.zdww.jssl_test.App
import com.zdww.jssl_test.lifecycle.*

/**
 * @desc 数据容器，可以监听数据的变化
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2020/11/12 22:40
 */
class DataListenContainer<T> {
    //存储监听
    private val blocks = arrayListOf<(T?)->Unit>()

    // 以监听为键，view的生命周期为值，来监听view的生命周期变化，同时，根据状态的变化，可以通知是否需要更新UI
    private val viewLifecycleProvider = hashMapOf<(T?)->Unit,LifecycleProvider>()

    //传过来需要监听的数据
    var value: T? = null

    //当数据变化的时候，就通知更新
    set(value: T?) {
        //判断当前线程是不是主线程
        //如果是，则直接调用，否则切换到主线程在执行
        if(Looper.getMainLooper().thread === Thread.currentThread()) {
            //判断对应 view的生命周期是什么
            blocks.forEach {
                //设置view变化时 去通知更新
                dispatchValue(it,value)
            }
        } else {
            //切换到主线程
            App.handler.post {
                blocks.forEach {
                    //设置view变化时 去通知更新
                    dispatchValue(it,value)
                }
            }
        }
    }

    /**
     * @desc 判断View当前的生命周期，看view是否可见，如果可见通知view更新UI
     * @author 强周亮
     * @time 2020/11/13 14:09
     */
    private fun dispatchValue(it: (T?) -> Unit, value: T?) {
        val viewModelProvider = viewLifecycleProvider[it]
        //判断view生命周期是否销毁，如果销毁则不在更新数据
        if (viewModelProvider != null && viewModelProvider.isAtLeast(LifeState.START)){
            println("更新UI")
            it.invoke(value)
        }
    }

    //添加数据监听,有可能有多个view进行监听，所有owner 对应 valueObserver，我们要管理起来
    fun addListener(owner: ILifecycleOwner, valueObserver:(T?)->Unit){
        //添加view生命周期监听
        val lifecycleProvider = owner.getLifecycleOwner()
        viewLifecycleProvider[valueObserver] = lifecycleProvider

        // 当view destroy的时候，要从集合中删除
        val observerWrapper = ValueObserverWrapper(valueObserver)
        lifecycleProvider.addLifecycleListener(observerWrapper)

        if (!blocks.contains(valueObserver)){
            blocks.add(valueObserver)
        }
    }

    inner class ValueObserverWrapper(private val valueObserver:(T?)->Unit):AbsLifecycle() {
        override fun onViewLifeStateChange(state: LifeState) {
            //当监听到当前的view的生命周期为destroy时，就把lifecycleProvider从集合中删除
            if (state == LifeState.DESTROY){
                viewLifecycleProvider.remove(valueObserver)
            }
        }
    }
}