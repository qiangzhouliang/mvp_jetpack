package com.zdww.jssl_test.player

import com.zdww.jssl_test.player.domain.Music

/**
 * @desc
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 */
class PlayerModel {
    fun getMusicById(id: String): Music {
        return Music("歌曲名称：$id", "img", "https://www.xxx.net")
    }

}