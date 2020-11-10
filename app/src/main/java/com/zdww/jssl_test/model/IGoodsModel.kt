package com.zdww.jssl_test.model

import com.zdww.jssl_test.bean.Goods

/**
 * @desc 数据源
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2020/11/10 12:02
 * @class jssl-test
 * @package com.zdww.jssl_test.model
 */
interface IGoodsModel {
    //给表示层提供数据
    fun showGoodsView(onLoadListerer: OnLoadListerer)

    interface OnLoadListerer {
        fun onComplete(goods: List<Goods>)
        fun onError(msg: String)
    }
}