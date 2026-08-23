package premiumbutchers.meat.premiumbutcherpick.data.model

import androidx.annotation.StringRes
import premiumbutchers.meat.premiumbutcherpick.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    BEEF(R.string.ruwcz_category_beef),
    LAMB(R.string.ruwcz_category_lamb),
    POULTRY(R.string.ruwcz_category_poultry),
    SAUSAGES(R.string.ruwcz_category_sausages),
    DELI(R.string.ruwcz_category_deli)
}
