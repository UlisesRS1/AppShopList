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
import com.shop.appshoplist.utils.ProductAdapter;
import com.shop.appshoplist.utils.ProductAdapterCheckList;

import java.util.List;

public class ScreenPurchased extends AppCompatActivity {

    private ListView purchasedList;
    private EditText edtTotal;
    private Button btnNotPurchased;
    private List<Product> products;

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
    }

    private void calculateAndSetTotal(){
        double total = 0;
        for (Product product: this.products){
            total += product.getPrice() * product.getQuantity();
        }
        total = Math.floor(total * 100) /100;
        this.edtTotal.setText(String.valueOf(total));
    }

    public void run(){
        init();

        IProductRepository iProductRepository = new InMemoryProductRepository();
        this.products = iProductRepository.getAllProducts();

        calculateAndSetTotal();

        this.purchasedList.setAdapter(new ProductAdapterCheckList(this, this.products));
    }
}