package com.zdww.jssl_test.lifecycle

/**
 * @desc 管理注册进来的接口，这个接口就是ILifecycle
 * 保存当前view的生命周期状态
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 */
class LifecycleProvider {
    private val lifecycleListener = arrayListOf<AbsLifecycle>()

    //当前生命周期状态
    var currentLifeState:LifeState = LifeState.DESTROY

    // 添加生命周期监听
    fun addLifecycleListener(listener:AbsLifecycle){
        if (!lifecycleListener.contains(listener)){
            lifecycleListener.add(listener)
        }
    }

    //移出生命周期监听
    fun removeLifecycleListener(listener:ILifecycle){
        lifecycleListener.remove(listener)
    }

    fun makeLifeState(state: LifeState){
        currentLifeState = state
        lifecycleListener.forEach { it.onViewLifeStateChange(state) }

        when(state){
            LifeState.CREATE -> {dispatchCreateState()}
            LifeState.START -> {dispatchStartState()}
            LifeState.RESUME -> {dispatchResumeState()}
            LifeState.PAUSE -> {dispatchPauseState()}
            LifeState.STOP -> {dispatchStopState()}
            LifeState.DESTROY -> {dispatchDestroyState()}
        }
    }

    private fun dispatchStopState() {
        lifecycleListener.forEach { it.onStop() }
    }

    private fun dispatchResumeState() {
        lifecycleListener.forEach { it.onResume() }
    }

    private fun dispatchStartState() {
        lifecycleListener.forEach { it.onStart() }
    }

    private fun dispatchPauseState() {
        lifecycleListener.forEach { it.onPause() }
    }

    private fun dispatchDestroyState() {
        lifecycleListener.forEach { it.onDestroy() }
        //清空监听
        lifecycleListener.clear()
    }

    private fun dispatchCreateState() {
        lifecycleListener.forEach { it.onCreate() }
    }

    //至少，判断是否可以更新UI
    fun isAtLeast(state: LifeState): Boolean {
        println("current state $currentLifeState === $state")
        val isAtLeastState = currentLifeState > state
        println("isAtLeastState == > $isAtLeastState")
        return currentLifeState > state
    }
}