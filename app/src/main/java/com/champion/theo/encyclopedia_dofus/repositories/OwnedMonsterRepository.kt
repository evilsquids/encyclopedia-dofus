package com.champion.theo.encyclopedia_dofus.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.champion.theo.encyclopedia_dofus.dal.room.RoomDofusDataSource
import com.champion.theo.encyclopedia_dofus.models.OwnedMonster

class OwnedMonsterRepository private constructor(application: Application) {
    val dataSource: RoomDofusDataSource

    init {
        dataSource = RoomDofusDataSource(application)
    }
    fun getOwnedMonsters(): LiveData<List<OwnedMonster>> = dataSource.ownedMonsters

    fun delete(ownedMonster: OwnedMonster) = dataSource.deleteOwnedMonster(ownedMonster)
    fun createOwnedMonster(ownedMonster: OwnedMonster) = dataSource.createOwnedMonster(ownedMonster)

    companion object {
        private var instance: OwnedMonsterRepository? = null

        // On crée un méthode qui retourne l'instance courante du repository si elle existe ou en crée une nouvelle sinon
        fun getInstance(application: Application): OwnedMonsterRepository {
            if (instance == null) {
                instance = OwnedMonsterRepository(application)
            }
            return instance!!
        }
    }

}