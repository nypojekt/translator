package by.dro.app.translator.ui.meaning

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.dro.app.translator.R
import by.dro.app.translator.extensions.loadUrl
import by.dro.app.translator.model.Meaning
import by.dro.app.translator.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_meaning.*
import org.koin.android.viewmodel.ext.android.viewModel

class MeaningFragment : Fragment(R.layout.fragment_meaning) {

    companion object {
        private const val ARG_IDS = "ARG_IDS"

        @JvmStatic
        fun newInstance(param1: String) =
            MeaningFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_IDS, param1)
                }
            }
    }

    private val viewModel: MeaningViewModel by viewModel()
    private var mediaPlayer: MediaPlayer? = null
    private var meaningItem: Meaning? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            it.getString(ARG_IDS)?.let {id ->

                Log.d("kkk", viewModel.toString())
                viewModel.getMeanings(id)
            }

        }
        observe()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_meaning_play_sound.setOnClickListener {
            meaningItem?.soundUrl?.also {
                playAudio(it)
            }
        }
    }

    private fun bind(meaning: Meaning){
        meaningItem = meaning
        fragment_meaning_text.text = meaning.text
        fragment_meaning_translation.text = meaning.translation?.text
        fragment_meaning_update_date.text = meaning.updatedAt
        if (!meaning.images.isNullOrEmpty())  fragment_meaning_img.loadUrl(meaning.images[0].url)

    }

    private fun observe(){
        viewModel.getMeaningsLiveData().observe(this, Observer {
            if (!it.isNullOrEmpty()) bind(it[0])
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
        = when(item.itemId){
            android.R.id.home ->{
                activity?.supportFragmentManager?.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }

    private fun playAudio(url: String) {
        killMediaPlayer()
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setDataSource("https:$url")
        mediaPlayer?.prepare()
        mediaPlayer?.start()
    }

    private fun killMediaPlayer() {
        try {
            mediaPlayer?.release()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}