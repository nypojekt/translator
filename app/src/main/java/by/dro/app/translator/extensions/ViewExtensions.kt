package by.dro.app.translator.extensions

import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import com.bumptech.glide.Glide


fun SearchView.onQueryTextChange(action: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            return true
        }
        override fun onQueryTextChange(newText: String): Boolean {
            action.invoke(newText)
            return true
        }
    })
}

fun ImageView.loadUrl(url: String?){
    Glide.with(this)
        .load("https:$url")
        .into(this)
}