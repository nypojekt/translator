package by.dro.app.translator.ui.word.search.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.dro.app.translator.R
import by.dro.app.translator.extensions.loadUrl
import by.dro.app.translator.model.Meaning2
import kotlinx.android.synthetic.main.item_meaning_2.view.*

class MeaningAdapter(val callback: SearchWordAdapter.OnClickListener): ListAdapter<Meaning2, MeaningAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent, callback)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class ViewHolder(parent: ViewGroup, callback: SearchWordAdapter.OnClickListener):
        RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_meaning_2, parent, false)) {

        private var meaningItem: Meaning2? = null

        init {
            itemView.setOnClickListener { callback.onClickToMeaning(meaningItem?.id) }
        }


        fun bindTo(meaning2: Meaning2?){

            meaning2?.also {
                meaningItem = it
                itemView.item_meaning_part_of_speech_code.text = it.partOfSpeechCode
                itemView.item_meaning_transcription.text = it.transcription
                itemView.item_meaning_translation.text = it.translation?.text
                itemView.item_meaning_preview.loadUrl(it.previewUrl)
            }

        }


    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Meaning2>() {
            override fun areItemsTheSame(oldItem: Meaning2, newItem: Meaning2): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Meaning2, newItem: Meaning2): Boolean =
                oldItem == newItem
        }
    }
}