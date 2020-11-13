package com.zdww.jssl_test.jetpack.simple.livedata

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.zdww.jssl_test.R
import kotlinx.android.synthetic.main.activity_test_live_data.*

class TestLiveDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_live_data)

        //只做一件事情，观察数据 变化 监听
        MyLiveData.getInstance().info.observe(this, { t ->
            //把观察到的数据设置到button
            button.text = t
        })

        //触发
        Handler().postDelayed({
            MyLiveData.getInstance().info.value = "三秒钟后 3000"
        },3000)
    }
}