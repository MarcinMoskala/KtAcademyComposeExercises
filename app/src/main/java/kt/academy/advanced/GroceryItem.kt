package kt.academy.advanced

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kt.academy.R

@Composable
fun GroceryItem(
    name: String,
    quantity: Int,
    isBought: Boolean,
    category: GroceryItemCategory?,
    onToggleClicked: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = SpaceBetween,
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            GroceryItemCheckbox(
                isChecked = isBought,
                onClick = onToggleClicked,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = name,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (quantity > 1) {
                Text(
                    text = "$quantity",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            val categoryIcon = category?.icon
            if (categoryIcon != null) {
                Icon(
                    painterResource(id = categoryIcon),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(24.dp)
                        .background(Color.Blue, CircleShape)
                        .padding(4.dp)
                )
            }
        }
    }
}

@Composable
private fun GroceryItemCheckbox(
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val checkedColor: Color = Color.Blue
    val uncheckedColor: Color = Color.White
    val shape: Shape = CircleShape
    val checkboxColor: Color by animateColorAsState(if (isChecked) checkedColor else uncheckedColor)
    Box(
        modifier = modifier
            .clickable { onClick() }
            .toggleable(value = isChecked, onValueChange = { onClick() })
            .background(color = checkboxColor, shape = shape)
            .border(width = 1.5.dp, color = checkedColor, shape = shape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.Default.Check,
            contentDescription = null,
            tint = uncheckedColor,
            modifier = Modifier
                .padding(2.dp)
        )
    }
}

@Preview
@Composable
private fun GroceryItemApplePreview() {
    GroceryItem(
        name = "Apple",
        quantity = 2,
        isBought = false,
        category = GroceryItemCategory.FRUITS,
        onToggleClicked = {},
    )
}

@Preview
@Composable
private fun GroceryItemMeatPreview() {
    GroceryItem(
        name = "Chicken",
        quantity = 1,
        isBought = true,
        category = GroceryItemCategory.MEAT,
        onToggleClicked = {},
    )
}

@Preview
@Composable
private fun GroceryItemDairyPreview() {
    GroceryItem(
        name = "Milk",
        quantity = 1,
        isBought = false,
        category = GroceryItemCategory.DAIRY,
        onToggleClicked = {},
    )
}

@Preview
@Composable
private fun GroceryItemNoCategoryPreview() {
    GroceryItem(
        name = "Bread",
        quantity = 20,
        isBought = true,
        category = null,
        onToggleClicked = {},
    )
}

enum class GroceryItemCategory(@DrawableRes val icon: Int? = null) {
    FRUITS(R.drawable.apple),
    MEAT,
    DAIRY(R.drawable.milk),
}