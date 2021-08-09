package com.ylt.toursdeliberations.ui.main.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ylt.toursdeliberations.ui.main.MainViewModel
import com.ylt.toursdeliberations.ui.main.theme.Beige3
import com.ylt.toursdeliberations.ui.main.theme.Beige5
import com.ylt.toursdeliberations.ui.main.theme.MyApplicationComposeTheme
import com.ylt.toursdeliberations.ui.main.theme.ToursDelibTypography

@Composable
fun DeliberationApp(viewModel: MainViewModel) {

    // setup Status bar color
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Beige5,
            darkIcons = useDarkIcons
        )
    }

    // Loading data
    val records by viewModel.deliberationsList.observeAsState(emptyList())
    if (records.isEmpty()) {
        LiveDataLoadingComponentProgressionIndicator()
    }

    val navController = rememberNavController()

    MyApplicationComposeTheme {
        Scaffold (
            topBar = { MyTopBar() }
        ) { it ->
            NavHost (
                navController = navController,
                startDestination = "list",
                modifier = Modifier.padding(it)
            ) {
                composable("list") {
                    DeliberationsListView(records) {
                        navController.navigate("detail/$it")
                    }
                }

                composable(
                    route = "detail/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.StringType })
                ) { entry ->
                    val deliberationId = entry.arguments?.getString("id")
                    requireNotNull(deliberationId) { "dogId parameter wasn't found. Please make sure it's set!" }
                    val delib = viewModel.deliberation(deliberationId)
                    DeliberationDetail(recordLiveData = delib)
                }
            }
        }
    }
}

@Composable
fun LiveDataLoadingComponentProgressionIndicator() {
    CircularProgressIndicator(
        modifier = Modifier
            .wrapContentWidth(Alignment.CenterHorizontally)
            .wrapContentHeight(Alignment.CenterVertically)
    )
}

@Preview
@Composable
fun MyTopBar() {
    TopAppBar(
        title = { Text(style = ToursDelibTypography.h4, text = "Tours Délibération") },
        backgroundColor = Beige3
    )
}