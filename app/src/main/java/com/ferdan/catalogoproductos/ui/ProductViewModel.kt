package com.ferdan.catalogoproductos.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ferdan.catalogoproductos.data.AppDatabase
import com.ferdan.catalogoproductos.data.Product
import com.ferdan.catalogoproductos.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProductRepository(
        AppDatabase.getInstance(application).productDao()
    )

    val products: LiveData<List<Product>> = repository.products.asLiveData()

    fun getById(id: Int, onResult: (Product?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val product = repository.getById(id)
            withContext(Dispatchers.Main) { onResult(product) }
        }
    }

    fun insert(product: Product, onComplete: () -> Unit = {}) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(product)
            withContext(Dispatchers.Main) { onComplete() }
        }
    }

    fun update(product: Product, onComplete: () -> Unit = {}) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(product)
            withContext(Dispatchers.Main) { onComplete() }
        }
    }

    fun delete(product: Product, onComplete: () -> Unit = {}) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(product)
            withContext(Dispatchers.Main) { onComplete() }
        }
    }
}
