package com.ylt.toursdeliberations.ui.main.composable

import android.os.Bundle
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ylt.toursdeliberations.R
import com.ylt.toursdeliberations.model.Deliberation
import com.ylt.toursdeliberations.model.Record
import com.ylt.toursdeliberations.ui.main.theme.Beige5
import com.ylt.toursdeliberations.ui.main.theme.MyApplicationComposeTheme
import com.ylt.toursdeliberations.ui.main.theme.ToursDelibTypography

@Composable
fun DeliberationsListView(liveDataRecord: LiveData<List<Record>>, navController: NavController) {

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
    val records by liveDataRecord.observeAsState(emptyList())
    if (records.isEmpty()) {
        LiveDataLoadingComponent()
    } else {
        MyApplicationComposeTheme {
            Scaffold (
                topBar = { MyTopBar() }
            ) {
                LazyColumn {
                    items(records) { record ->

                        val bundle = Bundle()
                        bundle.putString("delibId", record.deliberation.delibId)

                        DeliberationsCardView (record.deliberation) {
                            navController.navigate(R.id.list_to_detail, bundle)
                        }
                        Divider()
                    }
                }

            }
        }
    }
}

@Composable
fun DeliberationsCardView(delib: Deliberation, onDeliberationClicked: () -> Unit) {

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            Modifier
                .padding(8.dp)
                .clickable(onClick = onDeliberationClicked)
        ) {

            Text(style = ToursDelibTypography.h6, text = delib.delibObjet.lowercase().replaceFirstChar { char -> char.uppercase() })
            Spacer(Modifier.size(20.dp))
            Text(style = ToursDelibTypography.subtitle1, fontStyle = FontStyle.Italic, text = delib.collNom)
        }
    }
}