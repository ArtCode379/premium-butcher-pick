package premiumbutchers.meat.premiumbutcherpick.ui.composable.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import premiumbutchers.meat.premiumbutcherpick.ui.theme.GradientEnd
import premiumbutchers.meat.premiumbutcherpick.ui.theme.GradientStart
import premiumbutchers.meat.premiumbutcherpick.ui.viewmodel.RUWCZSplashVM
import premiumbutchers.meat.premiumbutcherpick.R

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: RUWCZSplashVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToOnboarding: () -> Unit
) {
    val onboarded by viewModel.onboardedState.collectAsStateWithLifecycle()
    val scale = remember { Animatable(0.8f) }
    LaunchedEffect(Unit) {
        scale.animateTo(1f, tween(800))
        delay(700)
        if (onboarded) onNavigateToHomeScreen() else onNavigateToOnboarding()
    }
    Column(
        modifier = modifier.fillMaxSize().background(Brush.verticalGradient(listOf(GradientStart, GradientEnd))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.icon),
            contentDescription = null,
            modifier = Modifier.scale(scale.value)
        )
        Text(text = "Premium Butcher Pick", color = Color.White, style = MaterialTheme.typography.headlineMedium)
        Text(text = "Quality cuts, ready for collection", color = Color.White.copy(alpha = 0.85f), style = MaterialTheme.typography.bodyMedium)
    }
}
