package com.ferdan.catalogoproductos.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ferdan.catalogoproductos.R
import com.ferdan.catalogoproductos.data.Product
import com.ferdan.catalogoproductos.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ProductViewModel
    private lateinit var adapter: ProductAdapter
    private var allProducts: List<Product> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupViewModel()
        observeProducts()
    }

    private fun setupViews() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        adapter = ProductAdapter(
            onEdit = { product -> openForm(product.id) },
            onDelete = { product -> confirmDelete(product) }
        )

        binding.recyclerProducts.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
            setHasFixedSize(true)
        }

        binding.etSearch.doAfterTextChanged { text ->
            filterProducts(text?.toString().orEmpty())
        }

        binding.fabAdd.setOnClickListener { openForm() }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
    }

    private fun observeProducts() {
        viewModel.products.observe(this) { products ->
            allProducts = products
            filterProducts(binding.etSearch.text?.toString().orEmpty())
        }
    }

    private fun filterProducts(query: String) {
        val normalized = query.trim().lowercase(Locale.getDefault())
        val filtered = if (normalized.isBlank()) {
            allProducts
        } else {
            allProducts.filter { product ->
                product.name.lowercase(Locale.getDefault()).contains(normalized) ||
                    product.category.lowercase(Locale.getDefault()).contains(normalized) ||
                    product.description.lowercase(Locale.getDefault()).contains(normalized)
            }
        }

        adapter.submitList(filtered)
        binding.tvCount.text = getString(R.string.product_count, filtered.size)

        val empty = filtered.isEmpty()
        binding.tvEmptyTitle.text = if (allProducts.isEmpty()) {
            getString(R.string.empty_title)
        } else {
            getString(R.string.no_results_title)
        }
        binding.tvEmptyMessage.text = if (allProducts.isEmpty()) {
            getString(R.string.empty_message)
        } else {
            getString(R.string.no_results_message)
        }
        binding.emptyState.visibility = if (empty) android.view.View.VISIBLE else android.view.View.GONE
        binding.recyclerProducts.visibility = if (empty) android.view.View.GONE else android.view.View.VISIBLE
    }

    private fun openForm(productId: Int? = null) {
        val intent = Intent(this, ProductFormActivity::class.java)
        productId?.let { intent.putExtra(ProductFormActivity.EXTRA_PRODUCT_ID, it) }
        startActivity(intent)
    }

    private fun confirmDelete(product: Product) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.delete_title)
            .setMessage(getString(R.string.delete_message, product.name))
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.delete) { _, _ ->
                viewModel.delete(product)
            }
            .show()
    }
}
