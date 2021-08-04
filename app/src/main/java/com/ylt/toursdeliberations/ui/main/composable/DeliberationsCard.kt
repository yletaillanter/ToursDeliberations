package com.ylt.toursdeliberations.ui.main.composable

import android.os.Bundle
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.ui.tooling.preview.Preview
import com.ylt.toursdeliberations.R
import com.ylt.toursdeliberations.model.Deliberation
import com.ylt.toursdeliberations.model.Record

@Composable
fun DeliberationsListView(liveDataRecord: LiveData<List<Record>>, navController: NavController) {
    val records by liveDataRecord.observeAsState(emptyList())

    if (records.isEmpty()) {
        LiveDataLoadingComponent()
    } else {
        LazyColumn(modifier = Modifier.padding(8.dp)) {
            items(records) { record ->
                val bundle = Bundle()
                bundle.putString("delibId", record.deliberation.delibId)
                DeliberationsCardView(
                    record.deliberation
                ) { navController.navigate(R.id.list_to_detail, bundle) }
                Divider()
            }
        }
    }
}

@Composable
fun DeliberationsCardView(delib: Deliberation, onDeliberationClicked: () -> Unit) {

    Card(elevation = 12.dp) {
        Column(
            Modifier
                .padding(8.dp)
                .clickable(onClick = onDeliberationClicked)
        ) {

            Text(text = delib.delibObjet, fontSize = 18.sp)
            Spacer(Modifier.size(20.dp))
            Text(text = delib.collNom, fontStyle = FontStyle.Italic)
        }
    }
}

@Preview
@Composable
fun DeliberationsCardPreview() {
    DeliberationsCardView(getDummyRecord().first().deliberation,{})
}
