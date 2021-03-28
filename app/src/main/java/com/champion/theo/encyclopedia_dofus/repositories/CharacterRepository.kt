package com.champion.theo.encyclopedia_dofus.repositories

import androidx.lifecycle.LiveData
import com.champion.theo.encyclopedia_dofus.dal.online.CharacterOnlineDataSource
import com.champion.theo.encyclopedia_dofus.models.Character

class CharacterRepository {
    private val dataSource = CharacterOnlineDataSource()

    fun getCharacters(): LiveData<List<Character>> {
        return dataSource.getCharacters()
    }

    companion object {
        private var instance: CharacterRepository? = null

        fun getInstance(): CharacterRepository {
            if (instance == null) {
                instance = CharacterRepository()
            }
            return instance!!
        }
    }
}