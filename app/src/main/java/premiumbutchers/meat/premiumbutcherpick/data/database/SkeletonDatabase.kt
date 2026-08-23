package premiumbutchers.meat.premiumbutcherpick.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import premiumbutchers.meat.premiumbutcherpick.data.dao.CartItemDao
import premiumbutchers.meat.premiumbutcherpick.data.dao.OrderDao
import premiumbutchers.meat.premiumbutcherpick.data.database.converter.Converters
import premiumbutchers.meat.premiumbutcherpick.data.entity.CartItemEntity
import premiumbutchers.meat.premiumbutcherpick.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RUWCZDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}