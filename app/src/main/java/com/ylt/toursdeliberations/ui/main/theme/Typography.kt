package com.ylt.toursdeliberations.ui.main.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ylt.toursdeliberations.R

private val LouisGeorgeCafe = FontFamily (
    Font(R.font.louis_george_cafe)
)

val ToursDelibTypography = Typography(
    defaultFontFamily = LouisGeorgeCafe,
    h4 = TextStyle(
        fontFamily = LouisGeorgeCafe,
        fontWeight = FontWeight.W400,
        fontSize = 30.sp
    ),
    h5 = TextStyle(
        fontFamily = LouisGeorgeCafe,
        fontWeight = FontWeight.W400,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = LouisGeorgeCafe,
        fontWeight = FontWeight.W400,
        fontSize = 21.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = LouisGeorgeCafe,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = LouisGeorgeCafe,
        fontWeight = FontWeight.W300,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = LouisGeorgeCafe,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = LouisGeorgeCafe,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = LouisGeorgeCafe,
        fontWeight = FontWeight.W200,
        fontSize = 14.sp
    )
)