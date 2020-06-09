package by.dro.app.translator.model

import com.google.gson.annotations.SerializedName

data class Meaning(
    @SerializedName("id")
    val id: String?,
    @SerializedName("wordId")
    val wordId: Int?,
    @SerializedName("difficultyLevel") //title: There are 6 difficultyLevels: 1, 2, 3, 4, 5, 6.
    val difficultyLevel: Int?,
    @SerializedName("partOfSpeechCode")
    val partOfSpeechCode: String?,
/*    Available codes:
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
    @SerializedName("prefix")
    val prefix: String?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("soundUrl")
    val soundUrl: String?,
    @SerializedName("transcription")
    val transcription: String?,
    @SerializedName("properties")
    val properties: Properties?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("mnemonics")
    val mnemonics: String?,
    @SerializedName("translation")
    val translation: Translation?,
    @SerializedName("images")
    val images: List<Image>?,
    @SerializedName("definition")
    val definition: Definition?,
    @SerializedName("examples")
    val examples: List<Example>?,
    @SerializedName("meaningsWithSimilarTranslation")
    val meaningsWithSimilarTranslation: List<MeaningWithSimilarTranslation>?,
    @SerializedName("alternativeTranslations")
    val alternativeTranslations: List<AlternativeTranslation>?
)