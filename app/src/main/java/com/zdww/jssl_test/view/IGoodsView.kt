package com.zdww.jssl_test.view

import com.zdww.jssl_test.bean.Goods

/**
 * @desc 逻辑
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2020/11/10 11:57
 * @class jssl-test
 * @package com.zdww.jssl_test.view
 */
interface IGoodsView: IBaseView {
    //显示图片文字
    fun showGoodsView(goods: List<Goods>)

    //加载进度条

    //加载动画
}