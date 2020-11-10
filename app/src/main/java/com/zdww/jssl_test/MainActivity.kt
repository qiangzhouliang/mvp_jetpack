package com.zdww.jssl_test

import android.os.Bundle
import com.zdww.jssl_test.bean.Goods
import com.zdww.jssl_test.presenter.GoodsPresenter
import com.zdww.jssl_test.view.BaseActivity
import com.zdww.jssl_test.view.IGoodsView

class MainActivity: BaseActivity<IGoodsView,GoodsPresenter<IGoodsView>>(),IGoodsView{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        initData()
    }

    private fun initData() {
        presenter.fetch()
    }

    override fun showGoodsView(goods: List<Goods>) {
        println(goods)
    }

    override fun showErrorMessage(msg: String) {

    }

    override fun createPresenter(): GoodsPresenter<IGoodsView> {
        return GoodsPresenter()
    }

    override fun initAct(){
        //事件 jetpack观察者和被观察者的关系
        lifecycle.addObserver(presenter)
    }
}