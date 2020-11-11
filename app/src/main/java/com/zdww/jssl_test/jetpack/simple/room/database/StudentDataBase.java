package com.zdww.jssl_test.jetpack.simple.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @desc 数据库
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2020/11/11 18:29
 * @class jssl-test
 * @package com.zdww.jssl_test.jetpack.simple.room.database
 */
@Database(entities = {Student.class},version = 1,exportSchema = false)
public abstract class StudentDataBase extends RoomDatabase {
    private static StudentDataBase INSTANCE;
    public static synchronized StudentDataBase getDataBase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),StudentDataBase.class,"student")
                    //.allowMainThreadQueries() //允许在主线程中执行
                    .build();
        }
        return INSTANCE;
    }

    //对外暴露 增删改查dao
    public abstract StudentDao getStudentDao();
}
