package br.com.domotest.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        TodoEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): TodoDao

    companion object {

        fun createDatabase(application: Application): AppDatabase {
            return Room.databaseBuilder(application, AppDatabase::class.java, "database")
                .fallbackToDestructiveMigration(false)
                .build()
        }
    }
}