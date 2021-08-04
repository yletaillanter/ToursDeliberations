package com.ylt.toursdeliberations.ui.main.composable

import android.R
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.ylt.toursdeliberations.model.CR
import com.ylt.toursdeliberations.model.Delib
import com.ylt.toursdeliberations.model.Deliberation
import com.ylt.toursdeliberations.model.Record
import java.time.LocalDate
import java.time.Month
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp


@Composable
fun DeliberationDetail(recordLiveData: LiveData<List<Record>>) {

    val record by recordLiveData.observeAsState(initial = emptyList())

    if (record.isEmpty()) {
        LiveDataLoadingComponent()
    } else {
        LiveDataComponentList(record)
    }
}

@Composable
fun LiveDataComponentList(record: List<Record>) {

    val deliberation = record.first().deliberation
    val date = LocalDate.parse(deliberation.delibDate)
    val context = LocalContext.current

    Column(Modifier.padding(15.dp)) {
        Text("${deliberation.collNom} le ${date.dayOfMonth} ${getFrenchMonth(date.month)} ${date.year} ")
        Text(deliberation.typeSeance)
//        Text(deliberation.themes, fontStyle = FontStyle.Italic)
        Text(deliberation.delibObjet, Modifier.padding(top=10.dp, bottom = 10.dp))
        Card (shape = RoundedCornerShape(3.dp)){
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    horizontalArrangement = Arrangement.Center
            ) {
                ImageResourceThumb(com.ylt.toursdeliberations.R.drawable.thumb_up, "vote pour ", deliberation.votePour)
                Spacer(modifier = Modifier.padding(10.dp))
                ImageResourceThumb(com.ylt.toursdeliberations.R.drawable.thumb_down_24, "vote contre ", deliberation.voteContre)
            }
        }
        Spacer(Modifier.size(15.dp))
        Card (shape = RoundedCornerShape(3.dp)){
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                ImageResourcePDF("Compte-rendu", { intentPdf(deliberation.crUrl.id, context)})
                Spacer(modifier = Modifier.padding(10.dp))
                ImageResourcePDF("Délibération", { intentPdf(deliberation.delibUrl.id, context)})
            }
        }
    }
}

fun intentPdf(id: String, context: Context): Unit {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://data.tours-metropole.fr/explore/dataset/deliberations-tours-metropole-val-de-loire/files/${id}/download"))
    browserIntent.setDataAndType( Uri.parse("https://data.tours-metropole.fr/explore/dataset/deliberations-tours-metropole-val-de-loire/files/${id}/download"),"application/pdf")
    startActivity(context, browserIntent, null)
}

fun getFrenchMonth(month: Month?): String {
    when(month?.name) {
        "JANUARY" -> return "Janvier"
        "FEBRUARY" -> return "Février"
        "MARCH" -> return "Mars"
        "APRIL" -> return "Avril"
        "MAY" -> return "Mai"
        "JUNE" -> return "Juin"
        "JULY" -> return "Juillet"
        "AUGUST" -> return "Aout"
        "SEPTEMBER" -> return "Septembre"
        "OCTOBRE" -> return "Octobre"
        "NOVEMBER" -> return "Novembre"
        "DECEMBER" -> return "Décembre"
        else -> return ""
    }
}


@Composable
fun ImageResourceThumb(drawable : Int, voteText: String, vote: Int) {
    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        val image: Painter = painterResource(id = drawable)
        Text(
            fontStyle = FontStyle.Italic,
            fontSize = 35.sp,
            text = "$vote"
        )
        Image(painter = image,contentDescription = "$voteText $vote")
    }
}

@Composable
fun ImageResourcePDF(text: String, onClick: ()->Unit) {
    Column (
        Modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val image: Painter = painterResource(id = com.ylt.toursdeliberations.R.drawable.picture_pdf)
        Image(painter = image,contentDescription = text)
        Text(text)
    }
}

@Composable
fun LiveDataLoadingComponent() {
    CircularProgressIndicator(modifier = Modifier
        .wrapContentWidth(CenterHorizontally)
        .wrapContentHeight(CenterVertically))
}


// ##############################################
// ################ PREVIEW #####################
// ##############################################


@Preview
@Composable
fun previewDetail() {
    LiveDataComponentList(getDummyRecord())
}


fun getDummyRecord(): List<Record> {
    return listOf(
        Record (
            datasetid = "deliberations-tours-metropole-val-de-loire",
            recordid = "5019320c4b7ab70728d71966362860d3ec895a8a",
            record_timestamp = "2021-02-13T00:30:00+00:00",
            deliberation =
                Deliberation (
                    delibMatiereCode = "3.3",
                    voteEffectifs = 30,
                    delibObjet = "PARCAY-MESLAY - CHANCEAUX-SUR-CHOISILLE - ZAC DU CASSANTIN - PRET A USAGE A CONCLURE AVEC L EARL LA PERAUDERIE",
                    collNom = "Tours Métropole Val de Loire",
                    delibId = "B_21_02_04_006",
                    crUrl =
                        CR (format = "pdf", filename = "1613167005_cr_29631.pdf", width = "300", id = "a5339ff612c513fff6a77b77592be40c", height = "300", thumbnail = "false"),
                    collSiret = "24370075400035",
                    delibUrl =
                        Delib(format = "pdf", filename = "1613167005_29597.pdf", width = "300", id = "7c7e57fcc05f6e64fe7d66021971c855", height = "300", thumbnail = "false"),
                    delibDate = "2021-02-12",
                    votePour = 30,
                    delibMatiereNom = "- Domaine et patrimoine   - Locations",
                    themes = "Domaine et patrimoine - Locations",
                    typeSeance = "Bureau Métropolitain",
                    voteReel = 30,
                    voteAbstention = 0,
                    voteContre = 0,
                    prefId = "SPREF0372",
                    prefDate = "2021-02-12"
                )
    ))
}