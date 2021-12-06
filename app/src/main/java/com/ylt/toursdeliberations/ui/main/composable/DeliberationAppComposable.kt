package com.ylt.toursdeliberations.ui.main.composable

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ylt.toursdeliberations.ui.main.MainViewModel
import com.ylt.toursdeliberations.ui.main.theme.*

import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
@Composable
fun DeliberationApp(viewModel: MainViewModel) {

    // Loading data
    var date = "2021-01-01"
    val records by viewModel.deliberationsList.observeAsState(emptyList())
    val navController = rememberAnimatedNavController()

    MyApplicationComposeTheme {
        Scaffold (
            topBar = { MyTopBar() }
        ) { it ->
            AnimatedNavHost (
                navController = navController,
                startDestination = "list",
                modifier = Modifier.padding(it)
            ) {
                composable(
                    route = "list",
                    popExitTransition = { _, _ ->
                        fadeOut(animationSpec = tween(100))
                    },
                    popEnterTransition = { _, _ ->
                        fadeIn(animationSpec = tween(100))
                    }
                ) {
                    if (records.isEmpty()) {
                        DataLoadingProgressionIndicator()
                    } else {
                        DeliberationsListView(records) {
                            navController.navigate("detail/$it")
                        }
                    }
                }

                composable(
                    route = "detail/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.StringType }),
                    enterTransition = { _, _ ->
                        slideInHorizontally (
                            initialOffsetX = { 500 },
                            animationSpec = tween(
                                durationMillis = 100,
                                easing = FastOutSlowInEasing
                            )
                        )
                    },
                    exitTransition = { _, _ ->
                        slideOutHorizontally (
                            targetOffsetX = { 500 },
                            animationSpec = tween(
                                durationMillis = 100,
                                easing = FastOutSlowInEasing
                            )
                        )
                    }
                ) { entry ->
                    val deliberationId = entry.arguments?.getString("id")
                    requireNotNull(deliberationId) { "deliberation Id parameter wasn't found. Please make sure it's set!" }
                    val delib = viewModel.deliberation(deliberationId)
                    DeliberationDetail(delib)
                }
            }
        }
    }
}

@Composable
fun DataLoadingProgressionIndicator() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        CircularProgressIndicator()
    }
}

@Composable
fun MyTopBar() {
    TopAppBar(
        title = {
            Text(style = ToursDelibTypography.h4, text = "Tours Délibérations")
        },
        backgroundColor = Neutral0
    )
}