package kt.academy.advanced.accessibility

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kt.academy.FavoriteIcon

@Composable
@Preview
fun IconExample() {
    Icon(
        modifier = Modifier.clickable {},
        imageVector = FavoriteIcon,
        contentDescription = "Toggle Favorite"
    )
}

@Composable
@Preview
fun SizedIconExample() {
    Icon(
        modifier = Modifier
            .sizeIn(minWidth = 48.dp, minHeight = 48.dp)
            .clickable {},
        imageVector = FavoriteIcon,
        contentDescription = "Toggle Favorite"
    )
}

@Composable
@Preview
fun IconButtonExample() {
    IconButton(onClick = {}) {
        Icon(
            imageVector = FavoriteIcon,
            contentDescription = "Toggle Favorite"
        )
    }
}

@Composable
@Preview
fun FocusAndTraversals() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 100.dp, horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .semantics {
                        traversalIndex = 0f
                    },
                value = "",
                onValueChange = {},
                label = {
                    Text(text = "First Name")
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .semantics {
                        traversalIndex = 2f
                    },
                value = "",
                onValueChange = {},
                label = {
                    Text(text = "Street")
                }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .semantics {
                        traversalIndex = 1f
                    },
                value = "",
                onValueChange = {},
                label = {
                    Text(text = "Last Name")
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .semantics {
                        traversalIndex = 3f
                    },
                value = "",
                onValueChange = {},
                label = {
                    Text(text = "Number")
                }
            )
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    isTraversalGroup = false
                    traversalIndex = 4f
                },
            value = "",
            onValueChange = {},
            label = {
                Text(text = "City")
            }
        )
    }
}