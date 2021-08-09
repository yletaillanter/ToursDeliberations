package com.ylt.toursdeliberations.ui.main.composable

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.ylt.toursdeliberations.ui.main.theme.Beige3
import com.ylt.toursdeliberations.ui.main.theme.ToursDelibTypography

@Composable
fun MyTopBar() {
    TopAppBar(
        title = { Text(style = ToursDelibTypography.h4, text = "Tours Délibération") },
        backgroundColor = Beige3
    )
}