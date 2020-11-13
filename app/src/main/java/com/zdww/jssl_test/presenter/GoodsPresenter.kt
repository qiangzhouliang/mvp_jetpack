package com.zdww.jssl_test.presenter

import android.widget.Switch
import androidx.lifecycle.LifecycleOwner
import com.zdww.jssl_test.R
import com.zdww.jssl_test.bean.Goods
import com.zdww.jssl_test.bean.ItemBean
import com.zdww.jssl_test.bean.ItemData
import com.zdww.jssl_test.model.GoodsModel
import com.zdww.jssl_test.model.IGoodsModel
import com.zdww.jssl_test.model.IGoodsModel.OnLoadListerer
import com.zdww.jssl_test.view.IGoodsView

/**
 * @desc
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2020/11/10 14:34
 * @class jssl-test
 * @package com.zdww.jssl_test.presenter
 */
class GoodsPresenter<T : IGoodsView>:BasePresent<T>() {

    var iGoodsModel: IGoodsModel = GoodsModel()

    //执行业务逻辑
    fun fetch(){
        iGoodsModel.showGoodsView(object : OnLoadListerer {
            override fun onComplete(goods: List<Goods>) {
                // goods 上已经有数据了
                iGoodsView?.get()?.showGoodsView(goods)
            }

            override fun onError(msg: String) {
            }
        })
    }

    fun initData(){
        val list = ArrayList<ItemBean>()
        for (i in 0..9) {
            when (i%2) {
                0 -> {
                    list.add(ItemBean("一个被收废品耽误的“段子手”火了：每天有几万人等着看他又捡了啥宝贝……","2020-11-22 12:00:00",
                        R.drawable.img1))
                }
                1 -> {
                    list.add(ItemBean("扬眉吐气！全民节日双十一，完美谢幕，天猫总成交额再创新高，4982亿，讲真，" +
                            "这个成绩完全出乎意料，太不可思议了，无疑，这意味着中国经济的复苏，看来，人们都缓过来了。当然，" +
                            "此次表现最出色的就是小米","2020-11-22 12:00:00",
                        R.drawable.img2))
                }
            }
        }
        ItemData.getInstance().list.value = list
    }

    override fun onCreate(owner: LifecycleOwner){
        println("onCreate")
        fetch()
        initData()
    }

    override fun onDestory(owner: LifecycleOwner) {
        super.onDestory(owner)
        println("onDestory")
    }
}