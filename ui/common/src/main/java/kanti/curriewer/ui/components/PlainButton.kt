package kanti.curriewer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.curriewer.ui.CurriewerTheme
import kanti.curriewer.ui.Green
import kanti.curriewer.ui.Red

@Composable
fun PlainButton(
	modifier: Modifier = Modifier,
	colors: PlainButtonColors = PlainButtonDefaults.greenColors(),
	contentPadding: PaddingValues = PaddingValues(all = 12.dp),
	onClick: () -> Unit,
	content: @Composable () -> Unit
) {
	val selfModifier = Modifier
		.clickable(
			interactionSource = remember { MutableInteractionSource() },
			indication = rememberRipple(),
			role = Role.Button,
			onClick = onClick
		)
		.background(
			color = colors.background,
			shape = RoundedCornerShape(10.dp)
		)
		.padding(contentPadding)
	Box(
		modifier = modifier.then(selfModifier),
		contentAlignment = Alignment.Center
	) {
		CompositionLocalProvider(
			LocalContentColor provides colors.content,
			LocalTextStyle provides MaterialTheme.typography.labelLarge
		) {
			content()
		}
	}
}

@Immutable
data class PlainButtonColors(
	val background: Color,
	val content: Color
)

object PlainButtonDefaults {

	@Composable
	fun redColors(
		background: Color = Red,
		content: Color = Color.White
	): PlainButtonColors = PlainButtonColors(
		background = background,
		content = content
	)

	@Composable
	fun greenColors(
		background: Color = Green,
		content: Color = Color.White
	): PlainButtonColors = PlainButtonColors(
		background = background,
		content = content
	)
}

@Preview
@Composable
private fun PreviewPlainButton() {
	CurriewerTheme {
		PlainButton(onClick = { }) {
			Text(text = "Plain Button")
		}
	}
}