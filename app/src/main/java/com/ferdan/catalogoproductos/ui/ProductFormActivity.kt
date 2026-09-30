package com.ferdan.catalogoproductos.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ferdan.catalogoproductos.R
import com.ferdan.catalogoproductos.data.Product
import com.ferdan.catalogoproductos.databinding.ActivityProductFormBinding

class ProductFormActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PRODUCT_ID = "product_id"
        private const val INVALID_ID = -1
    }

    private lateinit var binding: ActivityProductFormBinding
    private lateinit var viewModel: ProductViewModel
    private var editingId: Int = INVALID_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        editingId = intent.getIntExtra(EXTRA_PRODUCT_ID, INVALID_ID)

        setupToolbar()
        setupActions()

        if (editingId != INVALID_ID) {
            loadProduct(editingId)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = if (editingId == INVALID_ID) {
            getString(R.string.new_product)
        } else {
            getString(R.string.edit_product)
        }
    }

    private fun setupActions() {
        binding.btnSave.setOnClickListener {
            saveProduct()
        }
    }

    private fun loadProduct(id: Int) {
        viewModel.getById(id) { product ->
            if (product == null) {
                Toast.makeText(this, R.string.product_not_found, Toast.LENGTH_SHORT).show()
                finish()
                return@getById
            }

            binding.etName.setText(product.name)
            binding.etCategory.setText(product.category)
            binding.etPrice.setText(product.price.toString())
            binding.etStock.setText(product.stock.toString())
            binding.etDescription.setText(product.description)
        }
    }

    private fun saveProduct() {
        clearErrors()

        val name = binding.etName.text?.toString()?.trim().orEmpty()
        val category = binding.etCategory.text?.toString()?.trim().orEmpty()
        val priceText = binding.etPrice.text?.toString()?.trim().orEmpty()
        val stockText = binding.etStock.text?.toString()?.trim().orEmpty()
        val description = binding.etDescription.text?.toString()?.trim().orEmpty()

        var valid = true

        if (name.length < 3) {
            binding.tilName.error = getString(R.string.error_name)
            valid = false
        }

        if (category.isBlank()) {
            binding.tilCategory.error = getString(R.string.error_category)
            valid = false
        }

        val price = priceText.replace(',', '.').toDoubleOrNull()
        if (price == null || price <= 0.0) {
            binding.tilPrice.error = getString(R.string.error_price)
            valid = false
        }

        val stock = stockText.toIntOrNull()
        if (stock == null || stock < 0) {
            binding.tilStock.error = getString(R.string.error_stock)
            valid = false
        }

        if (description.length > 250) {
            binding.tilDescription.error = getString(R.string.error_description)
            valid = false
        }

        if (!valid) return

        val product = Product(
            id = if (editingId == INVALID_ID) 0 else editingId,
            name = name,
            category = category,
            price = price!!,
            stock = stock!!,
            description = description
        )

        if (editingId == INVALID_ID) {
            viewModel.insert(product) {
                Toast.makeText(this, R.string.product_created, Toast.LENGTH_SHORT).show()
                finish()
            }
        } else {
            viewModel.update(product) {
                Toast.makeText(this, R.string.product_updated, Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun clearErrors() {
        binding.tilName.error = null
        binding.tilCategory.error = null
        binding.tilPrice.error = null
        binding.tilStock.error = null
        binding.tilDescription.error = null
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
