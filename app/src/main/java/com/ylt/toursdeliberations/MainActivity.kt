package com.ylt.toursdeliberations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ylt.toursdeliberations.ui.main.composable.DeliberationApp
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.ylt.toursdeliberations.Injection.provideMainViewModelFactory
import com.ylt.toursdeliberations.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, provideMainViewModelFactory(applicationContext))[MainViewModel::class.java]

        setContent {
            DeliberationApp(viewModel)
        }
    }
}