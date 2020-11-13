package com.zdww.jssl_test.musicsList

import com.zdww.jssl_test.base.BaseFragment
import com.zdww.jssl_test.musicsList_jetpack.MusicPresenterJet

/**
 * @desc
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 */
class MusicDetailFragment: BaseFragment() {
    private val musicPresenter by lazy { MusicPresenterJet(this) }

}