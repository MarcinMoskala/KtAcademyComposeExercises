package kt.academy.advanced.accessibility

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import java.util.UUID

@Composable
@Preview
fun TransactionsList(
    modifier: Modifier = Modifier,
    transactions: ImmutableList<Transaction> = transactionsPreset,
) {
    LazyColumn(
        modifier = modifier.systemBarsPadding(),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 12.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = transactions, key = { it.id }) { transaction ->
            TransactionListItem(
                transaction = transaction,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(72.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(12.dp)
            )
        }
    }
}

@Composable
fun TransactionListItem(
    transaction: Transaction,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.semantics(mergeDescendants = true) {}
            .clearAndSetSemantics {
                contentDescription = if (transaction.isDeposit) {
                    "You have received " +
                            "${transaction.formattedAmount} from " +
                            "${transaction.origin} to your " +
                            transaction.destination
                } else {
                    "You have spent " +
                            "${transaction.formattedAmount} at " +
                            transaction.destination
                }
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (transaction.isDeposit) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = transaction.origin,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = transaction.ibanNumber,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            Text(
                modifier = Modifier.weight(1f),
                text = transaction.destination,
                style = MaterialTheme.typography.titleLarge
            )
        }
        val prefix = if (transaction.isDeposit) "+" else "-"
        val color = if (transaction.isDeposit) Color.Green else Color.Red
        Text(
            text = "$prefix ${transaction.formattedAmount}",
            style = MaterialTheme.typography.titleLarge,
            color = color
        )
    }
}

val transactionsPreset = persistentListOf(
    Transaction(
        id = UUID.randomUUID().toString(),
        origin = "Daily Account",
        ibanNumber = "NL 00 ABNA 1234 5678 90",
        amountInCents = (1..10_000).random().toDouble(),
        destination = "Nike",
        type = Transaction.TransactionType.Payment
    ),
    Transaction(
        id = UUID.randomUUID().toString(),
        origin = "Stripe",
        ibanNumber = "NL 00 ABNA 1234 5678 90",
        amountInCents = (1..10_000).random().toDouble(),
        destination = "Personal Account",
        type = Transaction.TransactionType.Deposit
    ),
    Transaction(
        id = UUID.randomUUID().toString(),
        origin = "Daily Account",
        ibanNumber = "NL 00 ABNA 1234 5678 90",
        amountInCents = (1..10_000).random().toDouble(),
        destination = "Car Service Ltd",
        type = Transaction.TransactionType.Payment
    ),
    Transaction(
        id = UUID.randomUUID().toString(),
        origin = "Daily Account",
        ibanNumber = "NL 00 ABNA 1234 5678 90",
        amountInCents = (1..10_000).random().toDouble(),
        destination = "Grocery Store",
        type = Transaction.TransactionType.Payment
    ),
    Transaction(
        id = UUID.randomUUID().toString(),
        origin = "Personal Account",
        ibanNumber = "NL 00 ABNA 1234 5678 91",
        amountInCents = (1..10_000).random().toDouble(),
        destination = "Savings Account",
        type = Transaction.TransactionType.Deposit
    ),
)

data class Transaction(
    val id: String,
    val origin: String,
    val ibanNumber: String,
    val amountInCents: Double,
    val destination: String,
    val type: TransactionType
) {
    enum class TransactionType {
        Deposit, Payment
    }

    val isDeposit: Boolean
        get() = type == TransactionType.Deposit

    val formattedAmount: String
        get() = String.format("$%.2f", amountInCents / 100.0)
}