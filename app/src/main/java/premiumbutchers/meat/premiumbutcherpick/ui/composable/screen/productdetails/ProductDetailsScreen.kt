package premiumbutchers.meat.premiumbutcherpick.ui.composable.screen.productdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel
import premiumbutchers.meat.premiumbutcherpick.R
import premiumbutchers.meat.premiumbutcherpick.data.model.Product
import premiumbutchers.meat.premiumbutcherpick.ui.composable.shared.RUWCZContentWrapper
import premiumbutchers.meat.premiumbutcherpick.ui.composable.shared.RUWCZEmptyView
import premiumbutchers.meat.premiumbutcherpick.ui.state.DataUiState
import premiumbutchers.meat.premiumbutcherpick.ui.viewmodel.ProductDetailsViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel()
) {
    val state by viewModel.productDetailsState.collectAsState()
    LaunchedEffect(productId) {
        viewModel.observeProductDetails(productId)
    }
    ProductDetailsContent(state, modifier, viewModel::addProductToCart)
}

@Composable
private fun ProductDetailsContent(state: DataUiState<Product>, modifier: Modifier, onAdd: () -> Unit) {
    var cartAdded by remember { mutableStateOf(false) }
    LaunchedEffect(cartAdded) {
        if (cartAdded) {
            delay(2000)
            cartAdded = false
        }
    }
    Column(modifier.fillMaxSize()) {
        RUWCZContentWrapper(
            dataState = state,
            dataPopulated = {
                val product = (state as DataUiState.Populated).data
                Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
                    AsyncImage(
                        product.imageUrl,
                        product.title,
                        Modifier.fillMaxWidth().height(300.dp),
                        contentScale = ContentScale.Crop
                    )
                    Column(Modifier.padding(20.dp)) {
                        Text(product.title, style = MaterialTheme.typography.titleLarge)
                        Text(
                            stringResource(R.string.ruwcz_price, product.price),
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Surface(shape = RoundedCornerShape(50), color = MaterialTheme.colorScheme.surfaceVariant) {
                            Text(stringResource(product.category.titleRes), Modifier.padding(horizontal = 12.dp, vertical = 6.dp))
                        }
                        Spacer(Modifier.height(20.dp))
                        Text("Selected by our butchers", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(8.dp))
                        Text(product.description, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Spacer(Modifier.height(24.dp))
                        Button(
                            onClick = {
                                onAdd()
                                cartAdded = true
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Add to Cart")
                        }
                    }
                }
                AnimatedVisibility(visible = cartAdded, enter = slideInVertically { it }, exit = fadeOut()) {
                    Surface(color = MaterialTheme.colorScheme.primary, modifier = Modifier.fillMaxWidth()) {
                        Text("✓ Added to cart", Modifier.padding(16.dp), color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            },
            dataEmpty = {
                RUWCZEmptyView(
                    modifier = Modifier.fillMaxSize(),
                    primaryText = stringResource(R.string.ruwcz_product_details_state_empty_primary_text)
                )
            }
        )
    }
}
