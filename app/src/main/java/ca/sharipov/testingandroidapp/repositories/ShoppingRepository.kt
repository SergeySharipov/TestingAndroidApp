package ca.sharipov.testingandroidapp.repositories

import androidx.lifecycle.LiveData
import ca.sharipov.testingandroidapp.data.local.ShoppingItem
import ca.sharipov.testingandroidapp.data.remote.responses.ImageResponse
import ca.sharipov.testingandroidapp.other.Resource
import retrofit2.Response

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}