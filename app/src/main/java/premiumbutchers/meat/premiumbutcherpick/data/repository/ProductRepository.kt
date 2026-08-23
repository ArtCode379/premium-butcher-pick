package premiumbutchers.meat.premiumbutcherpick.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import premiumbutchers.meat.premiumbutcherpick.data.model.Product
import premiumbutchers.meat.premiumbutcherpick.data.model.ProductCategory

class ProductRepository {
    private val products = listOf(
        Product(
            1, "Dry-Aged Ribeye", "Richly marbled ribeye, matured for 28 days for deep flavour and exceptional tenderness.",
            ProductCategory.BEEF, 18.95, "https://images.unsplash.com/photo-1603048297172-c92544798d5a?w=1200"
        ),
        Product(
            2, "Fillet Steak", "Hand-trimmed centre-cut beef fillet with a delicate texture and clean flavour.",
            ProductCategory.BEEF, 22.50, "https://images.unsplash.com/photo-1546964124-0cce460f38ef?w=1200"
        ),
        Product(
            3, "Sirloin Steak", "A classic steak with satisfying beefy taste and a fine cap of fat.",
            ProductCategory.BEEF, 14.75, "https://images.unsplash.com/photo-1588168333986-5078d3ae3976?w=1200"
        ),
        Product(
            4, "Lamb Cutlets", "Tender lamb cutlets prepared French-style, perfect with rosemary and garlic.",
            ProductCategory.LAMB, 16.40, "https://images.unsplash.com/photo-1603360946369-dc9bb6258143?w=1200"
        ),
        Product(
            5, "Lamb Shoulder", "Slow-cooking shoulder with generous flavour and natural succulence.",
            ProductCategory.LAMB, 13.90, "https://images.unsplash.com/photo-1514516816566-de580c621376?w=1200"
        ),
        Product(
            6, "Free-Range Chicken", "Whole free-range chicken with firm, juicy meat and crisp-skin potential.",
            ProductCategory.POULTRY, 11.25, "https://images.unsplash.com/photo-1587593810167-a84920ea0781?w=1200"
        ),
        Product(
            7, "Chicken Breast Fillets", "Lean, skinless chicken breast fillets, carefully trimmed.",
            ProductCategory.POULTRY, 8.80, "https://images.unsplash.com/photo-1604503468506-a8da13d82791?w=1200"
        ),
        Product(
            8, "Cumberland Sausages", "Coarsely cut pork sausages seasoned with pepper and sage.",
            ProductCategory.SAUSAGES, 6.95, "https://images.unsplash.com/photo-1585325701165-351af916e581?w=1200"
        ),
        Product(
            9, "Smoked Beef Sausages", "Oak-smoked beef sausages with a snappy casing and savoury finish.",
            ProductCategory.SAUSAGES, 7.50, "https://images.unsplash.com/photo-1552913908-ea9d4e9b2e6e?w=1200"
        ),
        Product(
            10, "Sliced Roast Beef", "Slow-roasted beef sliced thin for sandwiches and sharing boards.",
            ProductCategory.DELI, 6.25, "https://images.unsplash.com/photo-1628268909376-e8c44bb3153f?w=1200"
        ),
        Product(11, "Pastrami", "Pepper-crusted cured beef with aromatic spice and a tender bite.", ProductCategory.DELI, 7.20, "https://images.unsplash.com/photo-1544025162-d76694265947?w=1200"),
        Product(12, "Beef Burger Box", "Six thick, juicy beef patties made from selected cuts.", ProductCategory.BEEF, 12.00, "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=1200")
    )
    fun observeById(id: Int): Flow<Product?> = flowOf(products.find { it.id == id })
    fun getById(id: Int): Product? = products.find { it.id == id }
    fun observeAll(): Flow<List<Product>> = flowOf(products)
}
