package com.champion.theo.encyclopedia_dofus.dal.utils

import com.champion.theo.encyclopedia_dofus.dal.online.responses.CharacterResponse
import com.champion.theo.encyclopedia_dofus.dal.online.responses.CharacterResponseItem
import com.champion.theo.encyclopedia_dofus.dal.online.responses.SpellItemResponse
import com.champion.theo.encyclopedia_dofus.models.Character
import com.champion.theo.encyclopedia_dofus.models.Spell

fun CharacterResponseItem.toCharacter() = Character(
 ankamaId = ankamaId ?: "",
 description= description ?: "",
 femaleImg=femaleImg ?: "",
 id=id ?: "",
 level=level ?: "",
 maleImg=maleImg ?: "",
 name=name ?: "",
 roles= roles,
 spells= getSpellsFromResponse(spells),
 type=type,
 url=url
)

fun SpellItemResponse.toSpell() = Spell(
    name= name,
    variant = variant
)

fun getSpellsFromResponse(list: List<SpellItemResponse>?): List<Spell>? {
    var response: List<Spell>? = null

    if (list != null) {
        response =  list.map {
            it.toSpell()
        }
    }

    return response
}