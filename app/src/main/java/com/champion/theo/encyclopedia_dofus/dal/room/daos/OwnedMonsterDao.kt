package com.champion.theo.encyclopedia_dofus.dal.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.champion.theo.encyclopedia_dofus.dal.room.entities.OwnedMonsterEntity

@Dao
interface OwnedMonsterDao {
    @Query("SELECT * from ownedmonster")
    fun getMonsters(): LiveData<List<OwnedMonsterEntity>>

    @Insert
    fun add(monster: OwnedMonsterEntity)

    @Delete
    fun delete(monster: OwnedMonsterEntity)
}