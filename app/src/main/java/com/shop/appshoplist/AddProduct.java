package com.shop.appshoplist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.shop.appshoplist.data.repository.IProductRepository;
import com.shop.appshoplist.data.repository.InMemoryProductRepository;

public class AddProduct extends AppCompatActivity {
    private EditText edtProductName;
    private EditText edtProductPrice;
    private Button btnAgregar;
    private IProductRepository iProductRepository;
    private Intent getBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addProduct();
    }
    private void init(){
        this.btnAgregar = findViewById(R.id.btnAgregar);
        this.iProductRepository = new InMemoryProductRepository();
        this.getBack = new Intent(this,MainActivity.class);
    }

    public void addProduct(){
        init();
        getBackToMainScreen();


    }

    public void getBackToMainScreen(){

    }
}