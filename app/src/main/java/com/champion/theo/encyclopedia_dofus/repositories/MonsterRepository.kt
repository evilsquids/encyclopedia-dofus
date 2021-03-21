package com.champion.theo.encyclopedia_dofus.repositories

import androidx.lifecycle.LiveData
import com.champion.theo.encyclopedia_dofus.dal.online.MonsterOnlineDataSource
import com.champion.theo.encyclopedia_dofus.models.Monster

class MonsterRepository {
    private val dataSource = MonsterOnlineDataSource()
    fun getMonsters(type: String): LiveData<List<Monster>> {
        return dataSource.getMonsters(type)
    }


    companion object {
        private var instance: MonsterRepository? = null
        fun getInstance(): MonsterRepository {
            if (instance == null){
                instance = MonsterRepository()
            }
            return instance!!
        }
    }
}