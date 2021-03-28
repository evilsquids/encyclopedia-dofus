package com.champion.theo.encyclopedia_dofus.dal.online.responses

import com.google.gson.annotations.SerializedName

class CharacterResponse : ArrayList<CharacterItem>()

data class CharacterItem(
        @SerializedName("ankamaId")
        val ankamaId: Int?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("femaleImg")
        val femaleImg: String?,
        @SerializedName("_id")
        val id: Int?,
        @SerializedName("level")
        val level: Any?,
        @SerializedName("maleImg")
        val maleImg: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("roles")
        val roles: List<String>?,
        @SerializedName("spells")
        val spells: List<SpellItem>?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("url")
        val url: String?
)

data class SpellItem(
        @SerializedName("name")
        val name: String?,
        @SerializedName("variant")
        val variant: String?
)