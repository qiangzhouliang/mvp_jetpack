package com.zdww.jssl_test.jetpack.simple.room.database;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @desc dao层操作
 * @anthor qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2020/11/11 18:28
 */
@Dao
public interface StudentDao {
    @Insert
    void insertWords(Student... students);

    @Update
    void updateWords(Student... students);

    @Delete
    void deleteWords(Student... students);

    @Query("DELETE FROM student")
    void deleteAllWords();

    @Query("SELECT * FROM student ORDER BY ID DESC")
    public LiveData<List<Student>> getAllStudentLive();

}
