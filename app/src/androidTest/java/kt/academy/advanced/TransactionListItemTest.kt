package kt.academy.advanced

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import kt.academy.advanced.accessibility.Transaction.TransactionType.Deposit
import kt.academy.advanced.accessibility.Transaction.TransactionType.Payment
import kt.academy.advanced.accessibility.TransactionListItem
import kt.academy.advanced.accessibility.transactionsPreset
import org.junit.Rule
import org.junit.Test

class TransactionListItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun depositHasCorrectContentDescription() {
        val transaction = transactionsPreset.first { it.type == Deposit }

        composeTestRule.setContent {
            TransactionListItem(transaction)
        }

        composeTestRule.onNodeWithContentDescription(
            "You have received ${transaction.formattedAmount} from " +
                    "${transaction.origin} to your ${transaction.destination}"
        ).assertExists()
    }

    @Test
    fun depositTransactionShowsRelevantData() {
        val transaction = transactionsPreset.first { it.type == Deposit }

        composeTestRule.setContent {
            TransactionListItem(transaction)
        }

        composeTestRule.onNodeWithText(transaction.origin, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithText(transaction.ibanNumber, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithText(transaction.destination, useUnmergedTree = true).assertIsNotDisplayed()
        composeTestRule.onNodeWithText("+ ${transaction.formattedAmount}", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun paymentHasCorrectContentDescription() {
        val transaction = transactionsPreset.first { it.type == Payment }

        composeTestRule.setContent {
            TransactionListItem(transaction)
        }

        composeTestRule.onNodeWithContentDescription(
            "You have spent ${transaction.formattedAmount} at ${transaction.destination}"
        ).assertExists()
    }

    @Test
    fun paymentTransactionShowsRelevantData() {
        val transaction = transactionsPreset.first { it.type == Payment }

        composeTestRule.setContent {
            TransactionListItem(transaction)
        }

        composeTestRule.onNodeWithText(transaction.origin, useUnmergedTree = true).assertIsNotDisplayed()
        composeTestRule.onNodeWithText(transaction.ibanNumber, useUnmergedTree = true).assertIsNotDisplayed()
        composeTestRule.onNodeWithText(transaction.destination, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithText("- ${transaction.formattedAmount}", useUnmergedTree = true).assertIsDisplayed()
    }
}