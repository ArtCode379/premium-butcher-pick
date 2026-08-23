package premiumbutchers.meat.premiumbutcherpick.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(modifier.fillMaxSize().padding(20.dp)) {
        Text("About", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(18.dp)) {
                Text("Company", color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("PREMIUM BUTCHERS LTD", style = MaterialTheme.typography.titleMedium)
                HorizontalDivider(Modifier.padding(vertical = 16.dp))
                Text("App version", color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("1.0", style = MaterialTheme.typography.titleMedium)
            }
        }
        Spacer(Modifier.height(28.dp))
        Text("Help & legal", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://premiumbutchers.casa")))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.OpenInNew, contentDescription = null)
            Text(" Customer Support")
        }
        Text(
            "Support and company information are available on our website.",
            Modifier.padding(top = 12.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
