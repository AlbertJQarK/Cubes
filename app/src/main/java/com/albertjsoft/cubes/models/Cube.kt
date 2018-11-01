package com.albertjsoft.cubes.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Cube(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("items") var items: List<Item>? = null
) : Serializable