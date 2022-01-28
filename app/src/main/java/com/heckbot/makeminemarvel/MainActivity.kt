package com.heckbot.makeminemarvel

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.heckbot.makeminemarvel.details.DetailsFragment
import com.heckbot.makeminemarvel.list.ListFragment

class MainActivity : AppCompatActivity() {
    val comicsViewModel by viewModels<ComicsViewModel>()
    val listFragment = ListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        comicsViewModel.viewStateLiveData.observe(this, {
            val fragment = when (it) {
                ComicsViewModel.ViewState.List -> listFragment
                ComicsViewModel.ViewState.Details -> DetailsFragment()
                else -> throw Exception("Invalid ViewState")
            }
            supportFragmentManager.transaction {
                replace(R.id.frame, fragment)
            }
        })
    }
}

fun FragmentManager.transaction(block: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction()
        .block()
        .commit()
}