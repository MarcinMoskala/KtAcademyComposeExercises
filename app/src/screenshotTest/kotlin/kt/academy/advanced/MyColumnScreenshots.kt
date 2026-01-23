package kt.academy.advanced

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.ComposableFunction0
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.tools.screenshot.PreviewTest

@PreviewTest
@Preview
@Composable
private fun MyColumnSimpleBoxesPreview() {
    MyColumn(
        modifier = Modifier.background(Blue.copy(alpha = 0.2f))
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Red)
        )
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Green)
        )
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Blue)
        )
    }
}

@PreviewTest
@Preview
@Composable
private fun MyColumnDifferentWidthsPreview() {
    MyColumn(
        modifier = Modifier.background(Blue.copy(alpha = 0.2f))
    ) {
        Box(
            modifier = Modifier
                .size(width = 50.dp, height = 50.dp)
                .background(Red)
        )
        Box(
            modifier = Modifier
                .size(width = 150.dp, height = 50.dp)
                .background(Green)
        )
        Box(
            modifier = Modifier
                .size(width = 100.dp, height = 50.dp)
                .background(Blue)
        )
    }
}

@PreviewTest
@Preview
@Composable
private fun MyColumnWithPaddingPreview() {
    MyColumn(
        modifier = Modifier
            .padding(20.dp)
            .background(Blue.copy(alpha = 0.2f))
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Red)
        )
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Green)
        )
    }
}

@PreviewTest
@Preview
@Composable
private fun MyColumnEmptyPreview() {
    MyColumn(
        modifier = Modifier.background(Red)
    ) {
        // Empty
    }
}

@PreviewTest
@Preview
@Composable
private fun MyColumnSingleChildPreview() {
    MyColumn(
        modifier = Modifier.background(Blue.copy(alpha = 0.2f))
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(Red)
        )
    }
}

@PreviewTest
@Preview
@Composable
private fun MyColumnTooManyChildrenPreview() {
    MyColumn(
        modifier = Modifier.background(Blue.copy(alpha = 0.2f))
    ) {
        repeat(100) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Red)
            )
        }
    }
}

@PreviewTest
@Preview
@Composable
private fun MyColumnTooManyChildrenAndBigOnePreview() {
    MyColumn(
        modifier = Modifier.background(Blue.copy(alpha = 0.2f))
    ) {
        repeat(100) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Red)
            )
        }

        Box(
            modifier = Modifier
                .size(600.dp)
                .background(Red)
        )
    }
}

@PreviewTest
@Preview
@Composable
private fun MyColumnLastItemCutPreview() {
    MyColumn(
        modifier = Modifier.size(100.dp, 250.dp)
    ) {
        Box(
            modifier = Modifier
                .border(3.dp, Color.Yellow)
                .size(100.dp)
                .background(Red)
        )
        Box(
            modifier = Modifier
                .border(3.dp, Color.Yellow)
                .size(100.dp)
                .background(Green)
        )
        Box(
            modifier = Modifier
                .border(3.dp, Color.Yellow)
                .size(100.dp)
                .background(Blue)
        )
    }
}

@PreviewTest
@Preview
@Composable
private fun MyColumnMinHeightPreview() {
    MyColumn(
        modifier = Modifier
            .size(100.dp, 200.dp)
            .background(Color.LightGray)
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Red)
        )
    }
}