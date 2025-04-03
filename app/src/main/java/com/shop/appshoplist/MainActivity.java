package com.shop.appshoplist;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.shop.appshoplist.data.model.Product;
import com.shop.appshoplist.data.repository.IProductRepository;
import com.shop.appshoplist.data.repository.InMemoryProductRepository;
import com.shop.appshoplist.utils.ProductAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView shopList;
    private List<Product> products;
    private EditText total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        run();
    }

    public void init(){
        this.shopList = findViewById(R.id.ltvListaDeProductos);
        this.total = findViewById(R.id.edtValor);
    }

    public void run(){
        init();
        IProductRepository iProductRepository = new InMemoryProductRepository();
        this.products = iProductRepository.getAllProducts();
        iProductRepository.addProduct(new Product("perro mojao, 120g", 120.99));

        this.shopList.setAdapter(new ProductAdapter(this, products));
    }

}