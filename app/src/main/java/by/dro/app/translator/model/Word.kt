package by.dro.app.translator.model

import com.google.gson.annotations.SerializedName

data class Word(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("meanings")
    val meanings: List<Meaning2>?
)