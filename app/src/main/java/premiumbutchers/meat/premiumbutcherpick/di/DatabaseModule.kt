package premiumbutchers.meat.premiumbutcherpick.di

import androidx.room.Room
import premiumbutchers.meat.premiumbutcherpick.data.database.RUWCZDatabase
import org.koin.dsl.module

private const val DB_NAME = "ruwcz_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = RUWCZDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<RUWCZDatabase>().cartItemDao() }

    single { get<RUWCZDatabase>().orderDao() }
}