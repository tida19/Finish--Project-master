package com.jirapat.todoapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ActivityDAO {

    @Insert
    fun insert(activity: Activity)

    @Query("SELECT * from activity_table")
    fun get(): LiveData<List<Activity>>

    @Query("DELETE  FROM activity_table")
    fun clear()

}