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

public class ScreenDidntPurchased extends AppCompatActivity {

    private ListView notPurchasedList;
    private TextView txtTotal;
    private Button btnNotPurchased;
    private Button btnRegresar;
    private List<Product> products;
    private Intent getBack;
    private Intent getToPurchased;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.screen_didnt_purchased);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        run();
    }

    private void init(){
        this.notPurchasedList = findViewById(R.id.ltvListaDeProductosNoSeleccionados);
        this.getBack = new Intent(this, MainActivity.class);
        this.getToPurchased = new Intent(this, ScreenPurchased.class);
    }

    private void calculateAndSetTotal(){
        double total = 0;
        for (Product product: this.products){
            total += product.getPrice() * product.getQuantity();
        }
        total = Math.floor(total * 100) /100;
        this.txtTotal.setText(String.valueOf(total));
    }

    private void getToMainScreen(){
        this.btnRegresar.setOnClickListener(v->{
            startActivity(this.getBack);
            finish();
        });
    }

    private List<Product> getAllNotProductPurchased(){
        IProductRepository iProductRepository = new InMemoryProductRepository();
        List<Product> products = iProductRepository.getAllProducts();
        List<Product> productNotPurchased = new ArrayList<>();

        for (Product product : products) {
            if (!product.isChecked()) {
                productNotPurchased.add(product);
            }
        }

        return productNotPurchased;
    }

    private void getToPurchasedScreen(){
        this.btnNotPurchased.setOnClickListener(v -> {
            startActivity(this.getToPurchased);
            finish();
        });
    }

    public void run(){
        init();
        this.products = getAllNotProductPurchased();
        calculateAndSetTotal();
        this.notPurchasedList.setAdapter(new ProductAdapterCheckList(this, this.products));

        getToPurchasedScreen();
        getToMainScreen();
    }
}