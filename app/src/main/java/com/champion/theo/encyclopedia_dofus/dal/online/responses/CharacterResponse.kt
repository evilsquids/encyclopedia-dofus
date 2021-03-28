package com.champion.theo.encyclopedia_dofus.dal.online.responses

import com.google.gson.annotations.SerializedName

class CharacterResponse : ArrayList<CharacterResponseItem>()

data class CharacterResponseItem(
        @SerializedName("ankamaId")
        val ankamaId: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("femaleImg")
        val femaleImg: String?,
        @SerializedName("_id")
        val id: String?,
        @SerializedName("level")
        val level: Any?,
        @SerializedName("maleImg")
        val maleImg: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("roles")
        val roles: List<String>?,
        @SerializedName("spells")
        val spells: List<SpellItemResponse>?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("url")
        val url: String?
)

data class SpellItemResponse(
        @SerializedName("name")
        val name: String?,
        @SerializedName("variant")
        val variant: String?
)