package kt.academy.polished.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark

@PreviewLightDark
@Composable
private fun ThemedComposables() {
    AppTheme {
        Column {
            AppPrimaryButton(label = "Click Me", onClick = {})
            AppCheckBox(label = "Check Me", checked = false, onCheckedChange = {})
        }
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
        shape = AppTheme.shapes.pill,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.accent,
            contentColor = AppTheme.colors.onAccent
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
        horizontalArrangement = Arrangement.spacedBy(AppTheme.sizes.sm)
    ) {
        Checkbox(
            colors = CheckboxDefaults.colors(
                checkedColor = AppTheme.colors.accent
            ),
            checked = checked,
            onCheckedChange = onCheckedChange,
        )
        Text(
            text = label,
            color = AppTheme.colors.onAccent,
            modifier = Modifier.padding(end = AppTheme.sizes.sm)
        )
    }
}