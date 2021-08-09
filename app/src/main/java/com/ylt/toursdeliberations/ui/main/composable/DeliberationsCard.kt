package com.ylt.toursdeliberations.ui.main.composable

import android.os.Bundle
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.ylt.toursdeliberations.model.Deliberation
import com.ylt.toursdeliberations.model.Record
import com.ylt.toursdeliberations.ui.main.theme.ToursDelibTypography

@Composable
fun DeliberationsListView(records: List<Record>, onClickDeliberation: (String) -> Unit) {

    LazyColumn {
        items(records) { record ->
            val bundle = Bundle()
            bundle.putString("delibId", record.deliberation.delibId)

            DeliberationsCardView (record.deliberation) {
                onClickDeliberation(it)
            }
            Divider()
        }
    }
}

@Composable
fun DeliberationsCardView(delib: Deliberation, onDeliberationClicked: (String) -> Unit) {

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            Modifier
                .padding(8.dp)
                .clickable(onClick = { onDeliberationClicked(delib.delibId)} )
        ) {

            Text(style = ToursDelibTypography.h6, text = delib.delibObjet.lowercase().replaceFirstChar { char -> char.uppercase() })
            Spacer(Modifier.size(20.dp))
            Text(style = ToursDelibTypography.subtitle1, fontStyle = FontStyle.Italic, text = delib.collNom)
        }
    }
}