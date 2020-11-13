package com.zdww.jssl_test.bean;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

/**
 * @desc
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2020/11/12 14:27
 * @class jssl-test
 * @package com.zdww.jssl_test.bean
 */
public class ItemData {
    private static final ItemData ourInstance = new ItemData();

    public static ItemData getInstance() {
        return ourInstance;
    }

    //定义一个数据
    private MutableLiveData<List<ItemBean>> list;

    //对外暴露 LiveData Data
    public MutableLiveData<List<ItemBean>> getList(){
        if (list == null){
            list = new MutableLiveData<>();
        }
        return list;
    }
}
