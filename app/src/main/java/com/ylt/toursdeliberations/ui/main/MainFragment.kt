package com.ylt.toursdeliberations.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.ylt.toursdeliberations.Injection.provideMainViewModelFactory
import androidx.navigation.fragment.findNavController

import com.ylt.toursdeliberations.ui.main.composable.DeliberationsListView

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this, provideMainViewModelFactory(requireActivity().applicationContext)).get(MainViewModel::class.java)

        return ComposeView(requireContext()).apply {
            setContent {
                DeliberationsListView(viewModel, findNavController())
            }
        }
    }
}