package premiumbutchers.meat.premiumbutcherpick.ui.composable.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import premiumbutchers.meat.premiumbutcherpick.R
import premiumbutchers.meat.premiumbutcherpick.data.model.Product
import premiumbutchers.meat.premiumbutcherpick.data.model.ProductCategory
import premiumbutchers.meat.premiumbutcherpick.ui.composable.shared.RUWCZContentWrapper
import premiumbutchers.meat.premiumbutcherpick.ui.composable.shared.RUWCZEmptyView
import premiumbutchers.meat.premiumbutcherpick.ui.state.DataUiState
import premiumbutchers.meat.premiumbutcherpick.ui.viewmodel.ProductViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit
) {
    val state by viewModel.productsState.collectAsState()
    HomeContent(state, modifier, onNavigateToProductDetails)
}

@Composable
private fun HomeContent(
    productsState: DataUiState<List<Product>>,
    modifier: Modifier,
    onNavigate: (Int) -> Unit
) {
    var category by remember { mutableStateOf<ProductCategory?>(null) }
    Column(modifier.fillMaxSize()) {
        Row(
            Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text("Premium Butcher Pick", style = MaterialTheme.typography.titleLarge)
                Text("Freshly prepared for you", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            IconButton(onClick = { category = null }) {
                Icon(Icons.Default.Search, contentDescription = "Reset filters")
            }
        }
        RUWCZContentWrapper(
            dataState = productsState,
            dataPopulated = {
                val products = (productsState as DataUiState.Populated).data
                val shown = products.filter { category == null || it.category == category }
                Card(
                    Modifier.fillMaxWidth().height(190.dp).padding(horizontal = 16.dp).clickable { onNavigate(products.first().id) },
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Box {
                        AsyncImage(products.first().imageUrl, null, Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                        Text(
                            "Butcher's pick • ${stringResource(R.string.ruwcz_price, products.first().price)}",
                            Modifier.align(Alignment.BottomStart).fillMaxWidth().padding(16.dp),
                            color = androidx.compose.ui.graphics.Color.White,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
                LazyRow(
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item { AssistChip(onClick = { category = null }, label = { Text("All") }) }
                    items(ProductCategory.entries.size) { index ->
                        val item = ProductCategory.entries[index]
                        AssistChip(onClick = { category = item }, label = { Text(stringResource(item.titleRes)) })
                    }
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(shown, key = { it.id }) { product ->
                        ProductCard(product, onNavigate)
                    }
                }
            },
            dataEmpty = {
                RUWCZEmptyView(
                    modifier = Modifier.fillMaxSize(),
                    primaryText = stringResource(R.string.ruwcz_products_state_empty_primary_text)
                )
            }
        )
    }
}

@Composable
private fun ProductCard(product: Product, onNavigate: (Int) -> Unit) {
    Card(Modifier.clickable { onNavigate(product.id) }, shape = RoundedCornerShape(16.dp)) {
        AsyncImage(product.imageUrl, product.title, Modifier.fillMaxWidth().height(120.dp), contentScale = ContentScale.Crop)
        Column(Modifier.padding(12.dp)) {
            Text(product.title, style = MaterialTheme.typography.titleMedium)
            Text(stringResource(product.category.titleRes), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(stringResource(R.string.ruwcz_price, product.price), color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleMedium)
        }
    }
}
