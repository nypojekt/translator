package by.dro.app.translator.model

import com.google.gson.annotations.SerializedName

data class AlternativeTranslation(
    @SerializedName("text")
    val text: String?,
    @SerializedName("translation")
    val translation: Translation?
)