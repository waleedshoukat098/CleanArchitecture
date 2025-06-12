package com.techenthusis.learningsession.data.model

import com.google.gson.annotations.SerializedName
import com.techenthusis.learningsession.domain.model.CatFact

data class CatFactDTO(
    @SerializedName("fact") val factText: String = "",
    @SerializedName("length") val length: Int = 0
)

fun CatFactDTO.toDomain(): CatFact {
    return CatFact(
        fact = this.factText,
        length = this.length
    )
}
