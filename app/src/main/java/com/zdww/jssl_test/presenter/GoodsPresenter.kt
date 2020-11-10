package com.zdww.jssl_test.presenter

import android.arch.lifecycle.LifecycleOwner
import com.zdww.jssl_test.bean.Goods
import com.zdww.jssl_test.model.GoodsModel
import com.zdww.jssl_test.model.IGoodsModel
import com.zdww.jssl_test.model.IGoodsModel.OnLoadListerer
import com.zdww.jssl_test.view.IGoodsView
import java.lang.ref.WeakReference

/**
 * @desc
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2020/11/10 14:34
 * @class jssl-test
 * @package com.zdww.jssl_test.presenter
 */
class GoodsPresenter <T: IGoodsView>:BasePresent<T>() {

    var iGoodsModel: IGoodsModel = GoodsModel()

    //执行业务逻辑
    fun fetch(){
        iGoodsModel.showGoodsView(object : OnLoadListerer{
            override fun onComplete(goods: List<Goods>) {
                // goods 上已经有数据了
                iGoodsView?.get()?.showGoodsView(goods)
            }

            override fun onError(msg: String) {
            }
        })
    }

    override fun onCreate(owner: LifecycleOwner){
        println("onCreate")
        fetch()
    }

    override fun onDestory(owner: LifecycleOwner) {
        super.onDestory(owner)
        println("onDestory")
    }
}