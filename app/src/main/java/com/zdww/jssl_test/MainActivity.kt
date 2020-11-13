package com.zdww.jssl_test

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zdww.jssl_test.adapter.RecycleAdapterDome
import com.zdww.jssl_test.bean.Goods
import com.zdww.jssl_test.bean.ItemBean
import com.zdww.jssl_test.bean.ItemData
import com.zdww.jssl_test.presenter.GoodsPresenter
import com.zdww.jssl_test.view.BaseActivity
import com.zdww.jssl_test.view.IGoodsView
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity: BaseActivity<IGoodsView, GoodsPresenter<IGoodsView>>(),IGoodsView{

    private var listdata:ArrayList<ItemBean> = ArrayList<ItemBean>()
    private val RecycleAdapterDome by lazy { RecycleAdapterDome(this, listdata) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
        /*
        与ListView效果对应的可以通过LinearLayoutManager来设置
        与GridView效果对应的可以通过GridLayoutManager来设置
        与瀑布流对应的可以通过StaggeredGridLayoutManager来设置
        */
        //LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        /*
        与ListView效果对应的可以通过LinearLayoutManager来设置
        与GridView效果对应的可以通过GridLayoutManager来设置
        与瀑布流对应的可以通过StaggeredGridLayoutManager来设置
        */
        //LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        recycle_view.layoutManager = manager
        recycle_view.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        recycle_view.adapter = RecycleAdapterDome
    }

    private fun initListener() {
        //只做一件事情，观察数据 变化 监听
        ItemData.getInstance().list.observe(this, { list ->
            listdata.clear()
            listdata.addAll(list)
            RecycleAdapterDome.notifyDataSetChanged()
        })
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