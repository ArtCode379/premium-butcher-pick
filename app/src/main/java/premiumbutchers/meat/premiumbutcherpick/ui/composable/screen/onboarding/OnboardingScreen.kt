package premiumbutchers.meat.premiumbutcherpick.ui.composable.screen.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import premiumbutchers.meat.premiumbutcherpick.ui.viewmodel.RUWCZOnboardingVM

private data class Page(val title: String, val description: String, val icon: ImageVector)
private val pages = listOf(
    Page("Exceptional cuts", "Explore carefully selected beef, lamb, poultry and deli favourites.", Icons.Default.Restaurant),
    Page("Reserve in minutes", "Build your basket and send your collection details with a simple checkout.", Icons.Default.ShoppingBasket),
    Page("Fresh within 24 hours", "We prepare your order and hold it in store for collection for 24 hours.", Icons.Default.AccessTime)
)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: RUWCZOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit
) {
    val completed by viewModel.onboardingSetState.collectAsState()
    val pager = rememberPagerState { pages.size }
    val scope = rememberCoroutineScope()
    LaunchedEffect(completed) {
        if (completed) onNavigateToHomeScreen()
    }
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(state = pager, modifier = Modifier.weight(1f)) { index ->
            val page = pages[index]
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primaryContainer) {
                    Icon(page.icon, null, Modifier.padding(28.dp).size(64.dp), tint = MaterialTheme.colorScheme.primary)
                }
                Spacer(Modifier.height(36.dp))
                Text(page.title, style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(12.dp))
                Text(page.description, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            pages.indices.forEach { index ->
                Surface(
                    modifier = Modifier.size(if (pager.currentPage == index) 12.dp else 8.dp),
                    shape = CircleShape,
                    color = if (pager.currentPage == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                ) {}
            }
        }
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {
                if (pager.currentPage == pages.lastIndex) {
                    viewModel.setOnboarded()
                } else {
                    scope.launch { pager.animateScrollToPage(pager.currentPage + 1) }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (pager.currentPage == pages.lastIndex) "Get Started" else "Next")
        }
    }
}
