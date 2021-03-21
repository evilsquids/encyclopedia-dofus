package com.champion.theo.encyclopedia_dofus.di

import android.app.Application
import com.champion.theo.encyclopedia_dofus.repositories.OwnedMonsterRepository

object DI {
    lateinit var repository: OwnedMonsterRepository
    fun inject(application: Application) {
        repository = OwnedMonsterRepository.getInstance(application)
    }
}