package com.champion.theo.encyclopedia_dofus.models

import com.champion.theo.encyclopedia_dofus.dal.room.entities.OwnedMonsterEntity

class OwnedMonster (
    val id:Long,
    val ankamaId:String
){
    fun toEntity(): OwnedMonsterEntity {
        return OwnedMonsterEntity(ankamaId)
    }
}
