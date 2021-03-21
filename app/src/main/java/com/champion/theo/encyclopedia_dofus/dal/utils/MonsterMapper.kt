package com.champion.theo.encyclopedia_dofus.dal.utils

import com.champion.theo.encyclopedia_dofus.dal.online.responses.MonsterResponse
import com.champion.theo.encyclopedia_dofus.dal.online.responses.MonsterResponseItem
import com.champion.theo.encyclopedia_dofus.models.Monster

fun MonsterResponseItem.toMonster() = Monster(
        ankamaId= ankamaId ?: "",
        createdAt = createdAt ?: "",
        id = id,
        imgUrl = imgUrl ?: "",
        infosUrl = infosUrl ?: "",
        name = name ?: "",
        pa = pa ?: "",
        pm = pm ?: "",
        pvMax = pvMax ?: "",
        pvMin = pvMin ?: "",
        resAir = resAir ?: "",
        resEau = resEau ?: "",
        resFeu = resFeu ?: "",
        resTerre = resTerre ?: "",
        type = type ?: "",
        updatedAt = updatedAt ?: ""
)