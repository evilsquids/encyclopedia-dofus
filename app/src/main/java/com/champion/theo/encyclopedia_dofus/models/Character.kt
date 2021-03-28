package com.champion.theo.encyclopedia_dofus.models


data class Character(
    val ankamaId: String?,
    val description: String?,
    val femaleImg: String?,
    val id: String?,
    val level: Any?,
    val maleImg: String?,
    val name: String?,
    val roles: List<String>?,
    val spells: List<Spell>?,
    val type: String?,
    val url: String?
)

data class Spell(
    val name: String?,
    val variant: String?
)