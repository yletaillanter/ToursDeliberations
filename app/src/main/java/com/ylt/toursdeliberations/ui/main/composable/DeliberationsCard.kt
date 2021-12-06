package com.ylt.toursdeliberations.ui.main.composable

import android.content.res.Configuration
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ylt.toursdeliberations.R
import com.ylt.toursdeliberations.model.Deliberation
import com.ylt.toursdeliberations.model.Record
import com.ylt.toursdeliberations.ui.main.theme.ToursDelibTypography

@Composable
fun DeliberationsListView(records: List<Record>, onClickDeliberation: (String) -> Unit) {

    val listState = rememberLazyListState()
    LazyColumn (state = listState) {
        items(records) { record ->
            val bundle = Bundle()
            bundle.putString("delibId", record.deliberation.delibId)

            DeliberationsCardView (record.deliberation) {
                onClickDeliberation(it)
            }
        }
    }
}

@Composable
fun DeliberationsCardView(delib: Deliberation, onDeliberationClicked: (String) -> Unit) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Column(
            Modifier
                .padding(8.dp)
                .clickable(onClick = { onDeliberationClicked(delib.delibId) })
        ) {
            Text(style = ToursDelibTypography.subtitle1, text = delib.delibObjet.lowercase().replaceFirstChar { char -> char.uppercase() })

            Row {
                Row (verticalAlignment = Alignment.CenterVertically) {
                        Text(style = ToursDelibTypography.body2, fontStyle = FontStyle.Italic, text = delib.collNom)
                }
                Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        voteCard(deliberation = delib)
                }
            }
        }
    }
}

@Composable
fun voteCard(deliberation: Deliberation) {
    Row {
        val imageThumbUp: Painter = painterResource(id = R.drawable.thumb_up)
        Image(painter = imageThumbUp, contentDescription = "vote pour ${deliberation.votePour}", Modifier.scale(0.7f))
        Text(text = "${deliberation.votePour}", style = ToursDelibTypography.body2)

        Spacer(modifier = Modifier.padding(5.dp))

        val imageThumbDown: Painter = painterResource(id = R.drawable.thumb_down_24)
        Image(painter = imageThumbDown, contentDescription = "vote contre ${deliberation.voteContre}", Modifier.scale(0.7f))
        Text(text = "${deliberation.voteContre}", style = ToursDelibTypography.body2)
    }
}

// PREVIEW
@Preview(device = Devices.PIXEL_3A) // Samsung A3
@Preview(device = Devices.PIXEL_4_XL, uiMode = Configuration.UI_MODE_NIGHT_YES) // Samsung S20
@Composable
fun DeliberationsCardViewPreview() {
    DeliberationsCardView(delib = getDummyRecord()[0].deliberation, onDeliberationClicked = {})
}

@Preview(showSystemUi = true)
@Composable
fun DeliberationsListViewPreview() {
    DeliberationsListView(getDummyRecord()) {}
}