package ru.unisafe.room.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.unisafe.room.ShopListDatabase
import ru.unisafe.room.products.ProductsDao
import ru.unisafe.room.shopping_lists.ShoppingListsDao

@Module
@InstallIn(SingletonComponent::class)
class ShopListDatabaseModule {

    @Provides
    fun provideShopListDatabase(
        @ApplicationContext
        applicationContext: Context
    ): ShopListDatabase {
        return Room.databaseBuilder(applicationContext, ShopListDatabase::class.java, "shopList.db")
            .createFromAsset("initial_shop_list.db")
            .build()
    }

    @Provides
    fun provideShoppingListsDao(database: ShopListDatabase): ShoppingListsDao {
        return database.getShoppingListsDao()
    }

    @Provides
    fun provideProductsDao(database: ShopListDatabase): ProductsDao {
        return database.getProductsDao()
    }

}