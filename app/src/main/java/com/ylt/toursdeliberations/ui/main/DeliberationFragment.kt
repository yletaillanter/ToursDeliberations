package com.ylt.toursdeliberations.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ylt.toursdeliberations.Injection
import com.ylt.toursdeliberations.ui.main.composable.DeliberationDetail

class DeliberationFragment: Fragment() {

    private lateinit var viewModel: MainViewModel
    private var delibId = "-1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString("delibId")?.let { delibId ->
            this.delibId = delibId
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this, Injection.provideMainViewModelFactory(requireActivity().applicationContext)).get(MainViewModel::class.java)

        return ComposeView(requireContext()).apply {
            setContent {
                DeliberationDetail(viewModel.deliberation(delibId = delibId))
            }
        }
    }
}