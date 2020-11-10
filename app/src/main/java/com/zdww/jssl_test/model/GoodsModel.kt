package com.zdww.jssl_test.model

import com.zdww.jssl_test.bean.Goods

/**
 * @desc
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2020/11/10 14:25
 * @class jssl-test
 * @package com.zdww.jssl_test.model
 */
class GoodsModel: IGoodsModel {
    override fun showGoodsView(onLoadListerer: IGoodsModel.OnLoadListerer) {
        onLoadListerer.onComplete(getData())
    }

    private fun getData():List<Goods> {
        val data:ArrayList<Goods> = ArrayList()
        data.add(Goods("hello World1"))
        data.add(Goods("hello World2"))
        return data
    }
}