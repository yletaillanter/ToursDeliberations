package com.ylt.toursdeliberations.ui.main.composable

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.ylt.toursdeliberations.model.Deliberations
import com.ylt.toursdeliberations.model.Record
import com.ylt.toursdeliberations.ui.main.MainViewModel
import timber.log.Timber

@Composable
fun DeliberationsCardView(delib: Deliberations){
    val context = ContextAmbient.current

    Column(
        Modifier.padding(8.dp).clickable(onClick = { Toast.makeText(context,delib.toString(), Toast.LENGTH_SHORT).show()})
    ) {
        Text(text = delib.delibObjet, color = Color.Blue, fontSize = 18.sp)
        Text(text = delib.collNom, fontStyle = FontStyle.Italic)
    }
}

@Composable
fun DeliberationsListView(viewModel:MainViewModel) {
    val records: List<Record> by  viewModel.deliberationsList.observeAsState(listOf())

    LazyColumnFor(records) { record ->
        DeliberationsCardView(record.deliberation)
        Divider()
    }
}

@Preview
@Composable
fun DeliberationsCardPreview() {
    DeliberationsCardView(Deliberations(delibObjet = "APPROBATION DU COMPTE ADMINISTRATIF DE L EXERCICE 2019 DU BUDGET PRINCIPAL ET AFFECTATION DU RESULTAT.", collNom = "Tours Métropole Val de Loire"))
}
