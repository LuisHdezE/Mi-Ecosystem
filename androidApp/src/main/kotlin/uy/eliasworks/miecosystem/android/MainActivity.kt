package uy.eliasworks.miecosystem.android

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import uy.eliasworks.miecosystem.EcosystemInfo
import uy.eliasworks.miecosystem.identity.BootstrapIdentity
import uy.eliasworks.miecosystem.android.theme.toMaterialColorScheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BootstrapApp()
        }
    }
}

@Composable
private fun BootstrapApp() {
    val context = LocalContext.current
    val darkTheme = isSystemInDarkTheme()
    val colorScheme = BootstrapIdentity.theme.colors.toMaterialColorScheme()

    MaterialTheme(colorScheme = colorScheme) {
        BootstrapScreen()
    }
}

@Composable
private fun BootstrapScreen() {
    val info = EcosystemInfo()

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = BootstrapIdentity.product.displayName,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Technical Bootstrap",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary,
            )
            Spacer(Modifier.height(24.dp))
            Text(
                text = "Theme: ${BootstrapIdentity.theme.id}",
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = "Theme version: ${BootstrapIdentity.theme.version}",
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = "Shared identity: OK",
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Platform: Android",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}
