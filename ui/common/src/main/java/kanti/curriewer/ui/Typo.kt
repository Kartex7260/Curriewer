package kanti.curriewer.ui

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val SfProTextFontFamily = FontFamily(
    Font(resId = R.font.sfprotext_bold, weight = FontWeight.Bold, style = FontStyle.Normal),
    Font(resId = R.font.sfprotext_bolditalic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(resId = R.font.sfprotext_heavy, weight = FontWeight.ExtraBold, style = FontStyle.Normal),
    Font(resId = R.font.sfprotext_heavyitalic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
    Font(resId = R.font.sfprotext_light, weight = FontWeight.Light, style = FontStyle.Normal),
    Font(resId = R.font.sfprotext_lightitalic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(resId = R.font.sfprotext_medium, weight = FontWeight.Medium, style = FontStyle.Normal),
    Font(resId = R.font.sfprotext_mediumitalic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(resId = R.font.sfprotext_regular, weight = FontWeight.Normal, style = FontStyle.Normal),
    Font(resId = R.font.sfprotext_regularitalic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(resId = R.font.sfprotext_semibold, weight = FontWeight.SemiBold, style = FontStyle.Normal),
    Font(resId = R.font.sfprotext_semibolditalic, weight = FontWeight.SemiBold, style = FontStyle.Italic)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = SfProTextFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = SfProTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = SfProTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )
)