package kt.academy.advanced

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.animation.*
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kt.academy.AppleIcon
import kt.academy.MilkIcon

@Composable
fun GroceryItem(
    name: String,
    quantity: Int,
    isBought: Boolean,
    category: GroceryItemCategory?,
    modifier: Modifier = Modifier,
    onToggleClicked: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = SpaceBetween,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            GroceryItemCheckbox(
                isChecked = isBought,
                onClick = onToggleClicked,
                modifier = Modifier
            )
            Text(
                text = name,
                fontSize = 16.sp,
                modifier = Modifier
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (quantity > 1) {
                Text(
                    text = "$quantity",
                    fontSize = 16.sp,
                    modifier = Modifier
                )
            }
            val categoryIcon = category?.icon
            if (categoryIcon != null) {
                Icon(
                    categoryIcon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
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
    Icon(
        Icons.Default.Check,
        contentDescription = null,
        tint = uncheckedColor,
        modifier = modifier
    )
}

@Preview
@Composable
fun GroceryItemPreview() {
    Column {
        GroceryItem(
            name = "Apple",
            quantity = 2,
            isBought = false,
            category = GroceryItemCategory.FRUITS,
            onToggleClicked = {},
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth()
        )
        GroceryItem(
            name = "Chicken",
            quantity = 1,
            isBought = true,
            category = GroceryItemCategory.MEAT,
            onToggleClicked = {},
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth()
        )
        GroceryItem(
            name = "Bread",
            quantity = 20,
            isBought = true,
            category = null,
            onToggleClicked = {},
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth()
        )
        GroceryItem(
            name = "Bread",
            quantity = 20,
            isBought = true,
            category = null,
            onToggleClicked = {},
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

enum class GroceryItemCategory(val icon: ImageVector? = null) {
    FRUITS(AppleIcon),
    MEAT,
    DAIRY(MilkIcon),
}