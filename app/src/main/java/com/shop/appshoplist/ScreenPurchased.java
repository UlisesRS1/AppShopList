package com.shop.appshoplist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.shop.appshoplist.data.model.Product;
import com.shop.appshoplist.data.repository.IProductRepository;
import com.shop.appshoplist.data.repository.InMemoryProductRepository;
import com.shop.appshoplist.utils.ProductAdapterCheckList;

import java.util.ArrayList;
import java.util.List;

public class ScreenPurchased extends AppCompatActivity {

    private ListView purchasedList;
    private TextView txtTotal;
    private Button btnNotPurchased;
    private Button btnRegresar;
    private List<Product> products;
    private Intent getBack;
    private Intent getToNotPurchased;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.screen_purchased);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        run();
    }

    private void init(){
        this.purchasedList = findViewById(R.id.ltvListaDeProductosChecados);
        this.txtTotal = findViewById(R.id.txtValorComprados);
        this.btnNotPurchased = findViewById(R.id.btnVerNoComprados);
        this.btnRegresar = findViewById(R.id.btnVolverAtras);
        this.getBack = new Intent(this, MainActivity.class);
        this.getToNotPurchased = new Intent(this, ScreenDidntPurchased.class);
    }

    private void calculateAndSetTotal(){
        double total = 0;
        for (Product product: this.products){
            total += product.getPrice() * product.getQuantity();
        }
        total = Math.floor(total * 100) /100;
        this.txtTotal.setText(String.valueOf(total));
    }

    private List<Product> getAllProductPurchased(){
        IProductRepository iProductRepository = new InMemoryProductRepository();
        List<Product> products = iProductRepository.getAllProducts();
        List<Product> productPurchased = new ArrayList<>();

        for (Product product : products) {
            if (product.isChecked()) {
                productPurchased.add(product);
            }
        }

        return productPurchased;
    }

    private void getToNotPurchasedScreen(){
        this.btnNotPurchased.setOnClickListener(v -> {
            startActivity(this.getToNotPurchased);
            finish();
        });
    }

    private void getToMainScreen(){
        this.btnRegresar.setOnClickListener(v->{
            startActivity(this.getBack);
            finish();
        });
    }

    public void run(){
        init();
        this.products = getAllProductPurchased();
        calculateAndSetTotal();
        this.purchasedList.setAdapter(new ProductAdapterCheckList(this, this.products));

        getToNotPurchasedScreen();
        getToMainScreen();
    }
}