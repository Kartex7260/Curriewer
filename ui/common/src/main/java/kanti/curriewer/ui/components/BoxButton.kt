package kanti.curriewer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.curriewer.ui.CurriewerTheme
import kanti.curriewer.ui.R


private val boxButtonShape = RoundedCornerShape(10.dp)

@Composable
fun BoxButton(
	modifier: Modifier = Modifier,
	colors: BoxButtonColors = BoxButtonDefaults.toolbar1Colors(),
	contentPadding: PaddingValues = PaddingValues(all = 8.dp),
	onClick: () -> Unit,
	content: @Composable () -> Unit
) {
	Box(
		modifier = Modifier
			.background(
				color = colors.background,
				shape = boxButtonShape
			)
			.clip(boxButtonShape)
			.clickable(
				interactionSource = remember { MutableInteractionSource() },
				indication = rememberRipple(),
				enabled = true,
				role = Role.Button,
				onClick = onClick
			)
			.padding(contentPadding)
			.then(modifier),
		contentAlignment = Alignment.Center
	) {
		CompositionLocalProvider(
			LocalContentColor provides colors.content
		) {
			content()
		}
	}
}

@Immutable
data class BoxButtonColors(
	val background: Color,
	val content: Color
)

object BoxButtonDefaults {

	@Composable
	fun toolbar1Colors(
		background: Color = MaterialTheme.colorScheme.primary,
		content: Color = contentColorFor(backgroundColor = background)
	): BoxButtonColors = BoxButtonColors(
		background = background,
		content = content
	)

	@Composable
	fun toolbar2Colors(
		background: Color = MaterialTheme.colorScheme.secondary,
		content: Color = contentColorFor(backgroundColor = background)
	): BoxButtonColors = BoxButtonColors(
		background = background,
		content = content
	)

	@Composable
	fun searchColors(
		background: Color = MaterialTheme.colorScheme.tertiary,
		content: Color = contentColorFor(backgroundColor = background)
	): BoxButtonColors = BoxButtonColors(
		background = background,
		content = content
	)
}

@Preview
@Composable
private fun PreviewBoxButton() {
	CurriewerTheme {
		BoxButton(onClick = {  }) {
			Icon(
				painter = painterResource(id = R.drawable.wallet),
				contentDescription = null
			)
		}
	}
}