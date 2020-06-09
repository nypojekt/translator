package by.dro.app.translator.ui.word.search.views

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import by.dro.app.translator.model.Word
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_search_word.view.*

class SearchWordViewHolder(parent: View,callback: SearchWordAdapter.OnClickListener, pool: RecyclerView.RecycledViewPool): RecyclerView.ViewHolder(parent) {

    private var wordItem: Word? = null
    private val adapter = MeaningAdapter(callback)

    private val show = ObjectAnimator.ofFloat(itemView.item_search_word_arrow, "rotation", 0f, 90f)
        .setDuration(200)
    private val hide = ObjectAnimator.ofFloat(itemView.item_search_word_arrow, "rotation", 90f, 0f)
        .setDuration(200)


    private var isListVisible = true
    init {
        itemView.item_search_word_rv.setRecycledViewPool(pool)
        itemView.item_search_word_rv.adapter = adapter
        itemView.item_search_word_rv.addItemDecoration(DividerItemDecoration(parent.context, DividerItemDecoration.VERTICAL))
        itemView.item_search_word_layout.setOnClickListener {
            setListVisibility(!isListVisible)
        }
    }

    fun bindTo(word: Word?){
        word?.apply {
            itemView.item_search_word_title.text = text
            wordItem = word
            adapter.submitList(null)
            isListVisible = false
            itemView.item_search_word_arrow.rotation = 0f
        }
    }

    private fun setListVisibility(isVisible: Boolean){
        if (isVisible){
            adapter.submitList(wordItem?.meanings)
            show.start()
        }
        else{
            adapter.submitList(null)
            hide.start()
        }
        isListVisible = isVisible
    }

}