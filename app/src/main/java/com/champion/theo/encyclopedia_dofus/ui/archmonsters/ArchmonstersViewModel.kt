package com.champion.theo.encyclopedia_dofus.ui.archmonsters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.champion.theo.encyclopedia_dofus.di.DI
import com.champion.theo.encyclopedia_dofus.models.Monster
import com.champion.theo.encyclopedia_dofus.models.OwnedMonster
import com.champion.theo.encyclopedia_dofus.repositories.MonsterRepository
import com.champion.theo.encyclopedia_dofus.repositories.OwnedMonsterRepository
import java.util.concurrent.Executors

class ArchmonstersViewModel : ViewModel() {

    private val monsterRepository: MonsterRepository = MonsterRepository.getInstance()
    private val ownedMonsterRepository: OwnedMonsterRepository = DI.repository
    private val _monsters = MutableLiveData<List<Monster>>()
    val monsters: LiveData<List<Monster>>
        get() = _monsters

    val ownedMonsters: LiveData<List<OwnedMonster>>
        get() = ownedMonsterRepository.getOwnedMonsters()
    init {
        loadMonsters("Archi-monstres")
        Log.d("ownedMonster", ownedMonsters.value?.size.toString())
    }
    private fun loadMonsters(type: String) {
        Executors.newSingleThreadExecutor().execute {
            // ownedMonsterRepository.createOwnedMonster(OwnedMonster(0,"2278"))
            val result = monsterRepository.getMonsters(type)
            _monsters.postValue(result.value)
        }
    }
}
