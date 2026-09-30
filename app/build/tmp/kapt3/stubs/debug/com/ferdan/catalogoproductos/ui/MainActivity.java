package com.ferdan.catalogoproductos.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007H\u0002J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\rH\u0002J\u0012\u0010\u0013\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\u0019\u0010\u0016\u001a\u00020\r2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002\u00a2\u0006\u0002\u0010\u0019J\b\u0010\u001a\u001a\u00020\rH\u0002J\b\u0010\u001b\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/ferdan/catalogoproductos/ui/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/ferdan/catalogoproductos/ui/ProductAdapter;", "allProducts", "", "Lcom/ferdan/catalogoproductos/data/Product;", "binding", "Lcom/ferdan/catalogoproductos/databinding/ActivityMainBinding;", "viewModel", "Lcom/ferdan/catalogoproductos/ui/ProductViewModel;", "confirmDelete", "", "product", "filterProducts", "query", "", "observeProducts", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "openForm", "productId", "", "(Ljava/lang/Integer;)V", "setupViewModel", "setupViews", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.ferdan.catalogoproductos.databinding.ActivityMainBinding binding;
    private com.ferdan.catalogoproductos.ui.ProductViewModel viewModel;
    private com.ferdan.catalogoproductos.ui.ProductAdapter adapter;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.ferdan.catalogoproductos.data.Product> allProducts;
    
    public MainActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupViews() {
    }
    
    private final void setupViewModel() {
    }
    
    private final void observeProducts() {
    }
    
    private final void filterProducts(java.lang.String query) {
    }
    
    private final void openForm(java.lang.Integer productId) {
    }
    
    private final void confirmDelete(com.ferdan.catalogoproductos.data.Product product) {
    }
}