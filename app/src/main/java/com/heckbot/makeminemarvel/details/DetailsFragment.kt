package com.heckbot.makeminemarvel.details

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.heckbot.makeminemarvel.ComicsViewModel
import com.heckbot.makeminemarvel.Dependencies
import com.heckbot.makeminemarvel.R

class DetailsFragment : Fragment(R.layout.fragment_comic_details) {
    val comicsViewModel by activityViewModels<ComicsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val ivCover = view.findViewById<ImageView>(R.id.ivCover)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvDetails = view.findViewById<TextView>(R.id.tvDetails)
        comicsViewModel.selectedComicLiveData.observe(viewLifecycleOwner, {
            it?.let { comic ->
                Dependencies.imageLoader.loadBitmapFromUrlIntoImageView(
                    comic.coverUrl ?: "",
                    ivCover
                )
                tvTitle.text = comic.title
                tvDetails.text = comic.details
            }
        })

        activity?.apply {
            onBackPressedDispatcher.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    comicsViewModel.comicCleared()
                }
            })
        }
    }

}