package com.albertjsoft.cubes.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Item(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("url") var url: String? = null
) : Serializable