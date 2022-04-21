package com.ylt.toursdeliberations.ui.main.composable

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ylt.toursdeliberations.model.CR
import com.ylt.toursdeliberations.model.Delib
import com.ylt.toursdeliberations.model.Deliberation
import com.ylt.toursdeliberations.model.Record
import java.time.LocalDate
import java.time.Month
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.material.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import com.ylt.toursdeliberations.R
import com.ylt.toursdeliberations.ui.main.theme.*
import kotlinx.coroutines.flow.Flow

@ExperimentalAnimationApi
@Composable
fun DeliberationDetail(record: Flow<List<Record>>) {

    val record by record.collectAsState(initial = emptyList())

    if (record.isEmpty()) {
        DataLoadingProgressionIndicator()
    } else {
        ListOfRecord(record)
    }
}

@ExperimentalAnimationApi
@Composable
fun ListOfRecord(record: List<Record>) {

    var visibleLogo by remember { mutableStateOf(false)}
    visibleLogo = true

    val deliberation = record.first().deliberation
    val date = LocalDate.parse(deliberation.delibDate)
    val context = LocalContext.current

    Column {
        Column {
            Text(
                deliberation.delibObjet.lowercase().replaceFirstChar { char -> char.uppercase() },
                style = ToursDelibTypography.subtitle1,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                "${deliberation.collNom} le ${date.dayOfMonth} ${getFrenchMonth(date.month)} ${date.year} ",
                Modifier.padding(8.dp)
            )
            Text(deliberation.typeSeance, Modifier.padding(8.dp))

            var visible by remember { mutableStateOf(false) }
            Card(shape = RoundedCornerShape(4.dp)) {
                Column {
                    Spacer(Modifier.size(12.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        ImageResourceThumb(R.drawable.thumb_up, "vote pour ", deliberation.votePour)
                        Spacer(modifier = Modifier.padding(10.dp))
                        ImageResourceThumb(
                            R.drawable.thumb_down_24,
                            "vote contre ",
                            deliberation.voteContre
                        )
                    }

                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button (
                            onClick = { visible = !visible },
                            colors = ButtonDefaults.textButtonColors(backgroundColor = Beige0),
                            shape = MaterialTheme.shapes.medium,
                        ) { Text(stringResource(id = R.string.votes_detail)) }
                    }
                    Spacer(Modifier.size(15.dp))
                }
            }

            Spacer(Modifier.size(15.dp))

            // Card Detail des votes
            val density = LocalDensity.current
            AnimatedVisibility (
                visible = visible,
                enter = slideInVertically (
                    // Slide in from 40 dp from the top.
                    initialOffsetY = { with(density) { -40.dp.roundToPx() } }
                ) + expandVertically (
                    // Expand from the top.
                    expandFrom = Alignment.Top
                ) + fadeIn(
                    // Fade in with the initial alpha of 0.3f.
                    initialAlpha = 0.3f
                ),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.fillMaxWidth()) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(15.dp)) {
                        Text("Vote pour : ${deliberation.votePour}")
                        Text("Vote contre : ${deliberation.voteContre}")
                        Text("Abstention : ${deliberation.voteAbstention}")
                        Text("Vote réel : ${deliberation.voteReel}")
                        Text("Vote effectif : ${deliberation.voteEffectifs}")
                    }
                }
            }

            Spacer(Modifier.size(15.dp))

            Card(shape = RoundedCornerShape(4.dp)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ImageResourcePDF("Compte-rendu") {
                        intentPdf(
                            deliberation.crUrl.id,
                            context
                        )
                    }
                    Spacer(modifier = Modifier.padding(30.dp))
                    ImageResourcePDF("Délibération") {
                        intentPdf(
                            deliberation.delibUrl.id,
                            context
                        )
                    }
                }
            }
        }

        AnimatedVisibility (
            visible = visibleLogo,
            enter = fadeIn()
        ) {
            Column (Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    val image: Painter = painterResource(id = R.drawable.logo_tours_couleur)
                    Image(
                        painter = image,
                        contentDescription = "logo de la ville de Tours",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(60.dp)
                    )
                }
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
    return when(month?.name) {
        "JANUARY" -> "Janvier"
        "FEBRUARY" -> "Février"
        "MARCH" -> "Mars"
        "APRIL" -> "Avril"
        "MAY" -> "Mai"
        "JUNE" -> "Juin"
        "JULY" -> "Juillet"
        "AUGUST" -> "Aout"
        "SEPTEMBER" -> "Septembre"
        "OCTOBRE" -> "Octobre"
        "NOVEMBER" -> "Novembre"
        "DECEMBER" -> "Décembre"
        else -> ""
    }
}


@Composable
fun ImageResourceThumb(drawable : Int, voteText: String, vote: Int) {
    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        val image: Painter = painterResource(id = drawable)
        Image(painter = image,contentDescription = "$voteText $vote")
        Text(text = "$vote", style = ToursDelibTypography.h4)
    }
}

@Composable
fun ImageResourcePDF(text: String, onClick: ()->Unit) {
    Column (
        Modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val image: Painter = painterResource(id = com.ylt.toursdeliberations.R.drawable.picture_pdf)
        Image(painter = image,contentDescription = text, modifier = Modifier.size(60.dp))
        Text(text)
    }
}

// ##############################################
// ################ PREVIEW #####################
// ##############################################

@ExperimentalAnimationApi
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDetail() {
    ListOfRecord(getDummyRecord())
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
        ),
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
        ),
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
        ),
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
        ),
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
        ),
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
        ),
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
        ),
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
        )
    )
}