package com.ylt.toursdeliberations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ylt.toursdeliberations.ui.main.MainFragment
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}