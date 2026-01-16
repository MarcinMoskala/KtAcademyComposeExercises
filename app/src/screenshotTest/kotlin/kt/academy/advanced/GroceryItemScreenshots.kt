package kt.academy.advanced

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest

@PreviewTest
@Preview
@Composable
fun GroceryItemApplePreview() {
    GroceryItem(
        name = "Apple",
        quantity = 2,
        isBought = false,
        category = GroceryItemCategory.FRUITS,
        onToggleClicked = {},
    )
}

@PreviewTest
@Preview
@Composable
fun GroceryItemMeatPreview() {
    GroceryItem(
        name = "Chicken",
        quantity = 1,
        isBought = true,
        category = GroceryItemCategory.MEAT,
        onToggleClicked = {},
    )
}

@PreviewTest
@Preview
@Composable
fun GroceryItemDairyPreview() {
    GroceryItem(
        name = "Milk",
        quantity = 1,
        isBought = false,
        category = GroceryItemCategory.DAIRY,
        onToggleClicked = {},
    )
}

@PreviewTest
@Preview
@Composable
fun GroceryItemNoCategoryPreview() {
    GroceryItem(
        name = "Bread",
        quantity = 20,
        isBought = true,
        category = null,
        onToggleClicked = {},
    )
}