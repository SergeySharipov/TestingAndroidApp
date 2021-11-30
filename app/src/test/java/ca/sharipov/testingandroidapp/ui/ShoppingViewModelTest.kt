package ca.sharipov.testingandroidapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ca.sharipov.testingandroidapp.MainCoroutineRule
import ca.sharipov.testingandroidapp.getOrAwaitValueTest
import ca.sharipov.testingandroidapp.other.Constants
import ca.sharipov.testingandroidapp.other.Status
import ca.sharipov.testingandroidapp.repositories.FakeShoppingRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ShoppingViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ShoppingViewModel

    @Before
    fun setup() {
        viewModel = ShoppingViewModel(FakeShoppingRepository())
    }

    @Test
    fun `insert shopping item with empty field, returns error`() {
        viewModel.insertShoppingItem("name", "", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too long name, returns error`() {
        val string = buildString {
            for(i in 1..Constants.MAX_NAME_LENGTH+1){
                append(9)
            }
        }
        viewModel.insertShoppingItem(string, "5", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too long price, returns error`() {
        val price = buildString {
            for(i in 1..Constants.MAX_PRICE_LENGTH+1){
                append(9)
            }
        }
        viewModel.insertShoppingItem("name", "5", price)

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too high amount, returns error`() {
        viewModel.insertShoppingItem("name", "99999999999999999999", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with valid input, returns success`() {
        val string = buildString {
            for(i in 1..Constants.MAX_NAME_LENGTH){
                append(9)
            }
        }
        val price = buildString {
            for(i in 1..Constants.MAX_PRICE_LENGTH+1){
                append(9)
            }
        }
        viewModel.insertShoppingItem(string, Int.MAX_VALUE.toString(), price)

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }
}