package kanti.curriewer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.curriewer.ui.R

private val blue = Color(0xFF2F80ED)
private val green = Color(0xFF219653)
private val brush = Brush.linearGradient(
	0f to blue,
	.93f to green,
	start = Offset(200f, -200f),
	end = Offset(-100f, 100f)
)
private val shape = RoundedCornerShape(percent = 100)

@Composable
fun FloatingButton(
	modifier: Modifier = Modifier,
	onClick: () -> Unit,
	content: @Composable () -> Unit
) {

	Box(
		modifier = Modifier
			.size(70.dp)
			.background(
				brush = brush,
				shape = shape
			)
			.clip(shape)
			.clickable(
				interactionSource = remember { MutableInteractionSource() },
				indication = rememberRipple(),
				enabled = true,
				role = Role.Button,
				onClick = onClick
			)
			.then(modifier),
		contentAlignment = Alignment.Center
	) {
		CompositionLocalProvider(
			LocalContentColor provides MaterialTheme.colorScheme.onPrimary
		) {
			content()
		}
	}
}

@Preview
@Composable
private fun PreviewFloatingButton() {
	FloatingButton(
		onClick = { }
	) {
		Icon(painter = painterResource(id = R.drawable.plus), contentDescription = null)
	}
}