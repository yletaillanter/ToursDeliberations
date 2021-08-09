package com.ylt.toursdeliberations.ui.main.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Beige5 = Color(0xff63533A)
val Beige4 = Color(0xffBEA06E)
val Beige3 = Color(0xffC9A975)
val Beige2 = Color(0xffE3BE84)
val Beige1 = Color(0xffF0C98B)
val Beige0 = Color(0xffE8DDCC)


val Bleu4 = Color(0xff374263)
val Bleu3 = Color(0xff6B80C1)
val Bleu2 = Color(0xff6F85C9)
val Bleu1 = Color(0xff7D96E3)
val Bleu0 = Color(0xff849FF0)

val Neutral8 = Color(0xff121212)
val Neutral7 = Color(0xdef000000)
val Neutral6 = Color(0x99000000)
val Neutral5 = Color(0x61000000)
val Neutral4 = Color(0x1f000000)
val Neutral3 = Color(0x1fffffff)
val Neutral2 = Color(0x61ffffff)
val Neutral1 = Color(0xbdffffff)
val Neutral0 = Color(0xffffffff)

val FunctionalRed = Color(0xffd00036)
val FunctionalRedDark = Color(0xffea6d7e)
val FunctionalGreen = Color(0xff52c41a)
val FunctionalGrey = Color(0xfff6f6f6)
val FunctionalDarkGrey = Color(0xff2e2e2e)

const val AlphaNearOpaque = 0.95f


fun lightColors (
    primary: Color = Beige3,
    primaryVariant: Color = Beige1,
    secondary: Color = Bleu3,
    secondaryVariant: Color = Bleu0,
    background: Color = Neutral1,
    surface: Color = Neutral1,
    error: Color = FunctionalRed,
    onPrimary: Color = Neutral6,
    onSecondary: Color = Neutral6,
    onBackground: Color = Neutral6,
    onSurface: Color = Neutral6,
    onError: Color = Neutral1
): Colors = Colors(
    primary,
    primaryVariant,
    secondary,
    secondaryVariant,
    background,
    surface,
    error,
    onPrimary,
    onSecondary,
    onBackground,
    onSurface,
    onError,
    false
)

fun darkColors(
    primary: Color = Beige5,
    primaryVariant: Color = Beige4,
    secondary: Color = Bleu4,
    secondaryVariant: Color = Bleu4,
    background: Color = Neutral6,
    surface: Color = Neutral6,
    error: Color = Color(0xFFCF6679),
    onPrimary: Color = Neutral1,
    onSecondary: Color = Neutral1,
    onBackground: Color = Neutral1,
    onSurface: Color = Neutral1,
    onError: Color = Neutral0
): Colors = Colors(
    primary,
    primaryVariant,
    secondary,
    secondaryVariant,
    background,
    surface,
    error,
    onPrimary,
    onSecondary,
    onBackground,
    onSurface,
    onError,
    false
)



