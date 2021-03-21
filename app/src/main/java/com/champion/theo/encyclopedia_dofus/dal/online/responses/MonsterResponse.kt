package com.champion.theo.encyclopedia_dofus.dal.online.responses
import com.google.gson.annotations.SerializedName


class MonsterResponse : ArrayList<MonsterResponseItem>()

data class MonsterResponseItem(
    @SerializedName("ankama_id")
    val ankamaId: String?,
    @SerializedName("created_at")
    val createdAt: Any?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img_url")
    val imgUrl: String?,
    @SerializedName("infos_url")
    val infosUrl: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("pa")
    val pa: String?,
    @SerializedName("pm")
    val pm: String?,
    @SerializedName("pv_max")
    val pvMax: String?,
    @SerializedName("pv_min")
    val pvMin: String?,
    @SerializedName("res_air")
    val resAir: String?,
    @SerializedName("res_eau")
    val resEau: String?,
    @SerializedName("res_feu")
    val resFeu: String?,
    @SerializedName("res_terre")
    val resTerre: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("updated_at")
    val updatedAt: Any?
)