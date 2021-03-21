package com.champion.theo.encyclopedia_dofus.ui.archmonsters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.champion.theo.encyclopedia_dofus.models.Monster
import com.champion.theo.encyclopedia_dofus.repositories.MonsterRepository
import java.util.concurrent.Executors

class ArchmonstersViewModel : ViewModel() {

    private val repository: MonsterRepository = MonsterRepository.getInstance()
    private val _monsters = MutableLiveData<List<Monster>>()
    val monsters: LiveData<List<Monster>>
        get() = _monsters

    init {
        loadMonsters("Archi-monstres")
    }
    private fun loadMonsters(type: String) {
        Executors.newSingleThreadExecutor().execute{
            val result = repository.getMonsters(type)
            _monsters.postValue(result.value)
        }
    }
}