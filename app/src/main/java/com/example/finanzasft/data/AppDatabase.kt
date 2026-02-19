package com.example.finanzasft.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [ProductEntity::class], version = 6, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object{

        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val MIGRATION_5_6 = object : Migration(4, 5){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE products ADD COLUMN brand TEXT")
                db.execSQL("ALTER TABLE products ADD COLUMN description TEXT")
                db.execSQL("ALTER TABLE products ADD COLUMN photoPath TEXT")
                db.execSQL("ALTER TABLE products ADD COLUMN category TEXT")
            }
        }

        fun getDatabase(context: Context) : AppDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "finanzas_db_v6"
                )
                    .addMigrations(MIGRATION_5_6)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}