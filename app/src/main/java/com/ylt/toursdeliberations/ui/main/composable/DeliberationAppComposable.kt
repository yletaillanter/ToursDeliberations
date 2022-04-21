package com.ylt.toursdeliberations.ui.main.composable

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ylt.toursdeliberations.ui.main.MainViewModel
import com.ylt.toursdeliberations.ui.main.theme.*

import com.google.accompanist.navigation.animation.composable
import com.ylt.toursdeliberations.R
import timber.log.Timber

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DeliberationApp(viewModel: MainViewModel) {

    var date1 = "2021-01-01"
    var date2 = "2017-01-01"

    // Loading data
    var dateParameter by remember { mutableStateOf(date1) }
    val records by viewModel.deliberationsListByDate(dateParameter).collectAsState(initial = emptyList())
    val navController = rememberAnimatedNavController()

    MyApplicationComposeTheme {
        Scaffold (
            topBar = { MyTopBar { Timber.i("filter clicked") } }
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
fun MyTopBar(onClickFilter: () -> Unit) {
    TopAppBar(
        title = { Text(style = ToursDelibTypography.h4, text = "Tours Délibérations") },
        backgroundColor = Neutral0,
        actions = { Image(painter = painterResource(id = R.drawable.ic_baseline_filter_list_24), contentDescription = "filter", Modifier.clickable { onClickFilter() }) },
        modifier = Modifier.fillMaxWidth()
    )
}