package com.champion.theo.encyclopedia_dofus.dal.room.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "ownedmonster",indices = arrayOf(Index(value = ["ankamaId"],unique = true)) )
class OwnedMonsterEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val ankamaId:String
)