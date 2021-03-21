package com.champion.theo.encyclopedia_dofus.dal.room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.champion.theo.encyclopedia_dofus.dal.room.daos.OwnedMonsterDao
import com.champion.theo.encyclopedia_dofus.dal.utils.toOwnedMonster
import com.champion.theo.encyclopedia_dofus.models.Monster
import com.champion.theo.encyclopedia_dofus.models.OwnedMonster

class RoomDofusDataSource(application: Application) {
    private val database: DofusDataBase = DofusDataBase.getDataBase(application)
    private val dao: OwnedMonsterDao = database.ownedMonsterDao()

    private val _ownedMonsters = MediatorLiveData<List<OwnedMonster>>()

    init {
        _ownedMonsters.addSource(dao.getMonsters()) { entities ->
            _ownedMonsters.value = entities.map { entity ->

                entity.toOwnedMonster()
            }
        }
    }
    val ownedMonsters: LiveData<List<OwnedMonster>>
        get() = _ownedMonsters

    fun deleteOwnedMonster(ownedMonster: OwnedMonster): Int {
        TODO("Not yet implemented")
    }

    fun createOwnedMonster(ownedMonster: OwnedMonster) {
        dao.add(ownedMonster.toEntity())
    }

}