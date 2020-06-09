package by.dro.app.translator.model

import com.google.gson.annotations.SerializedName

data class Definition(
    @SerializedName("text")
    val text: String?,
    @SerializedName("soundUrl")
    val soundUrl: String?
)