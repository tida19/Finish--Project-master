package com.jirapat.todoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Activity::class], version = 1, exportSchema = false)
abstract class ActivityDatabase : RoomDatabase() {

    abstract val activityDao: ActivityDAO

    companion object {
        @Volatile
        private var INSTANCE: ActivityDatabase? = null
        fun getInstance(context: Context): ActivityDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ActivityDatabase::class.java,
                        "database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}