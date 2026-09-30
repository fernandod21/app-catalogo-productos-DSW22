package com.ferdan.catalogoproductos.repository

import com.ferdan.catalogoproductos.data.Product
import com.ferdan.catalogoproductos.data.ProductDao

class ProductRepository(private val dao: ProductDao) {

    val products = dao.observeAll()

    suspend fun getById(id: Int): Product? = dao.getById(id)

    suspend fun insert(product: Product) = dao.insert(product)

    suspend fun update(product: Product) = dao.update(product)

    suspend fun delete(product: Product) = dao.delete(product)
}
