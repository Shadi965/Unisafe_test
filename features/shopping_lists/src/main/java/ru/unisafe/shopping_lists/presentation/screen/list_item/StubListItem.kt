package ru.unisafe.shopping_lists.presentation.screen.list_item

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ShimmerAnimatedStubItem() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition(label = "LoadingAnim")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        ), label = "LoadingAnim"
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    StubItem(brush)
}

@Composable
fun StubItem(brush: Brush) {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .background(brush)
        .padding(8.dp)) {



        val (name, date, id) = createRefs()
        Text(text = "", fontSize = 24.sp,
            modifier = Modifier.constrainAs(name){}
        )
        Text(text = "", fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(date) {
                    top.linkTo(name.bottom)
                }
                .padding(top = 4.dp)
        )
        Text(text = "", fontSize = 20.sp,
            modifier = Modifier.constrainAs(id){
                end.linkTo(parent.end)
                bottom.linkTo(date.bottom)
            }
        )
    }
}