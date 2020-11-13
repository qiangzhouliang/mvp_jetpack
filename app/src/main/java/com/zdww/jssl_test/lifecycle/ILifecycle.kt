package com.zdww.jssl_test.lifecycle

/**
 * @desc 生命周期接口
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 */
interface ILifecycle {
    fun onViewLifeStateChange(state: LifeState)
}