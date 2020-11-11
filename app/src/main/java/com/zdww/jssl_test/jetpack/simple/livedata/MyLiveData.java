package com.zdww.jssl_test.jetpack.simple.livedata;

import android.arch.lifecycle.MutableLiveData;

/**
 * @desc LiveData 数据类
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2020/11/10 22:58
 * @class jssl-test
 * @package com.zdww.jssl_test.jetpack.simple.livedata
 */
class MyLiveData {
    //定义一个数据
    private MutableLiveData<String> info;

    //对外暴露 LiveData Data
    public MutableLiveData<String> getInfo(){
        if (info == null){
            info = new MutableLiveData<>();
        }
        return info;
    }

    //单例模式
    private static MyLiveData liveData = new MyLiveData();
    public static MyLiveData getInstance(){return liveData;};
}
