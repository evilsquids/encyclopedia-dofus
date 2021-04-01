package com.champion.theo.encyclopedia_dofus.adapters

import com.champion.theo.encyclopedia_dofus.models.Monster

interface ArchmonstersListHandler {
    fun addMonster(monster: Monster)
    fun removeMonster(monster: Monster)
}
