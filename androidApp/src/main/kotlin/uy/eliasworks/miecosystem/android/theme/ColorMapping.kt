package uy.eliasworks.miecosystem.android.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import uy.eliasworks.miecosystem.identity.ColorToken
import uy.eliasworks.miecosystem.identity.SemanticColors

fun ColorToken.toComposeColor(): Color = Color(argb.toULong())

fun SemanticColors.toMaterialColorScheme(): ColorScheme {
    return lightColorScheme(
        primary = primary.toComposeColor(),
        onPrimary = onPrimary.toComposeColor(),
        secondary = secondary.toComposeColor(),
        onSecondary = onSecondary.toComposeColor(),
        background = background.toComposeColor(),
        onBackground = onBackground.toComposeColor(),
        surface = surface.toComposeColor(),
        onSurface = onSurface.toComposeColor(),
        error = error.toComposeColor(),
        onError = onError.toComposeColor()
    )
}
