package by.dro.app.translator.model

import com.google.gson.annotations.SerializedName

data class MeaningWithSimilarTranslation(
    @SerializedName("meaningId")
    val meaningId: Int?,
    @SerializedName("frequencyPercent")
    val frequencyPercent: String?,
    @SerializedName("partOfSpeechAbbreviation")
    val partOfSpeechAbbreviation: String?,
    @SerializedName("translation")
    val translation: Translation?

)