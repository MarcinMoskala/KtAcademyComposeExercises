package kt.academy.polished.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp

@PreviewLightDark
@Composable
private fun ThemedComposables() {
    Column {
        AppPrimaryButton(label = "Click Me", onClick = {})
        AppCheckBox(label = "Check Me", checked = false, onCheckedChange = {})
    }
}

@Composable
fun AppPrimaryButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(percent = 50),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF0061A4),
            contentColor = Color.White
        )
    ) {
        Text(text = label)
    }
}

@Composable
fun AppCheckBox(
    label: String,
    checked: Boolean,
    onCheckedChange: (newValue: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Checkbox(
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF0061A4),
            ),
            checked = checked,
            onCheckedChange = onCheckedChange,
        )
        Text(
            text = label,
            color = Color.White,
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}