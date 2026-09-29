package uy.eliasworks.miecosystem.identity

object BootstrapIdentity {
    val theme = ProductTheme(
        id = "mi-ecosystem-default",
        version = "1.0.0",
        colors = SemanticColors(
            primary = ColorToken(0xFF006C4C),
            onPrimary = ColorToken(0xFFFFFFFF),
            secondary = ColorToken(0xFF4D6357),
            onSecondary = ColorToken(0xFFFFFFFF),
            background = ColorToken(0xFFFBFDF9),
            onBackground = ColorToken(0xFF191C1A),
            surface = ColorToken(0xFFFBFDF9),
            onSurface = ColorToken(0xFF191C1A),
            error = ColorToken(0xFFBA1A1A),
            onError = ColorToken(0xFFFFFFFF)
        )
    )

    val product = ProductIdentity(
        productId = "mi-ecosystem",
        displayName = "Mi Ecosystem",
        theme = theme
    )
}
