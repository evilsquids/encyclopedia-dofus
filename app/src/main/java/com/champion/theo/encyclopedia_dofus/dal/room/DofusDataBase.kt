package com.champion.theo.encyclopedia_dofus.dal.room

import android.app.Application
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.champion.theo.encyclopedia_dofus.dal.room.daos.OwnedMonsterDao
import com.champion.theo.encyclopedia_dofus.dal.room.entities.OwnedMonsterEntity
import java.util.concurrent.Executors

@Database(
    entities = [OwnedMonsterEntity::class],
    version = 1
)
abstract class DofusDataBase: RoomDatabase() {
    abstract fun ownedMonsterDao(): OwnedMonsterDao

    companion object {
        private var instance: DofusDataBase? = null
        fun getDataBase(application: Application): DofusDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    application.applicationContext,
                    DofusDataBase::class.java,
                    "dofus_database.db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.d("item", "tatata")
                    }
                })
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}