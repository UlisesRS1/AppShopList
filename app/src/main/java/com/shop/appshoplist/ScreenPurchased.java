package com.shop.appshoplist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
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
import com.shop.appshoplist.utils.ProductAdapterCheckList;

import java.util.ArrayList;
import java.util.List;

public class ScreenPurchased extends AppCompatActivity {

    private ListView purchasedList;
    private EditText edtTotal;
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
        this.edtTotal = findViewById(R.id.edtValorChecados);
        this.btnNotPurchased = findViewById(R.id.btnVerNoComprados);
        this.getBack = new Intent(this, MainActivity.class);
        this.getToNotPurchased = new Intent(this, ScreenDidntPurchased.class);
    }

    private void calculateAndSetTotal(){
        double total = 0;
        for (Product product: this.products){
            total += product.getPrice() * product.getQuantity();
        }
        total = Math.floor(total * 100) /100;
        this.edtTotal.setText(String.valueOf(total));
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

    public void run(){
        init();
        this.products = getAllProductPurchased();
        calculateAndSetTotal();
        this.purchasedList.setAdapter(new ProductAdapterCheckList(this, this.products));

        getToNotPurchasedScreen();
    }
}