package com.champion.theo.encyclopedia_dofus.ui.encyclopedia.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.champion.theo.encyclopedia_dofus.models.Character
import com.champion.theo.encyclopedia_dofus.repositories.CharacterRepository
import java.util.concurrent.Executors

class CharacterViewModel: ViewModel() {

    private val characterRepository: CharacterRepository = CharacterRepository.getInstance()

    private val _characters = MutableLiveData<List<Character>>()

    val characters: LiveData<List<Character>>
        get() = _characters

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        Executors.newSingleThreadExecutor().execute {
            val result = characterRepository.getCharacters()
            _characters.postValue(result.value)
        }
    }
}