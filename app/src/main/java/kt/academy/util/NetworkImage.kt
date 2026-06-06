package kt.academy.util

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.compose.rememberAsyncImagePainter

@SuppressLint("DiscouragedApi")
@Composable
fun NetworkImage(url: String, modifier: Modifier = Modifier) {
    val resourceName = remember(url) {
        extractResourceName(url)
    }

    val context = LocalContext.current
    val drawableId = remember(resourceName) {
        if (resourceName != null) {
            context.resources.getIdentifier(resourceName, "drawable", context.packageName)
        } else 0
    }

    val painter = if (drawableId != 0) {
        painterResource(drawableId)
    } else {
        rememberAsyncImagePainter(url)
    }

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier,
    )
}

internal fun extractResourceName(url: String): String? {
    val prefix = "https://marcinmoskala.com/courses/"
    if (!url.startsWith(prefix)) return null
    val parts = url.split("/")
    if (parts.size >= 6 && parts[parts.size - 2] == "resources") {
        return parts.last().substringBeforeLast(".")
    }
    return null
}