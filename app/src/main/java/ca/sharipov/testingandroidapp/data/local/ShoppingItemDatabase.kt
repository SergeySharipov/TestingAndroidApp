package ca.sharipov.testingandroidapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ca.sharipov.testingandroidapp.data.local.ShoppingDao
import ca.sharipov.testingandroidapp.data.local.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingItemDatabase : RoomDatabase() {

    abstract fun shoppingDao(): ShoppingDao
}