package by.dro.app.translator.model

import com.google.gson.annotations.SerializedName

data class Meaning2(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("partOfSpeechCode")
    val partOfSpeechCode: String,
/*
Available codes:
n - noun,
v - verb,
j - adjective,
r - adverb,
prp - preposition,
prn - pronoun,
crd - cardinal number,
cjc - conjunction,
exc - interjection,
det - article,
abb - abbreviation,
x - particle,
ord - ordinal number,
md - modal verb,
ph - phrase,
phi - idiom.*/
    @SerializedName("translation")
    val translation: Translation?,
    @SerializedName("previewUrl")
    val previewUrl: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("transcription")
    val transcription: String?,
    @SerializedName("soundUrl")
    val soundUrl: String?
)