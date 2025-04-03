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

import com.shop.appshoplist.data.model.Product;
import com.shop.appshoplist.data.repository.IProductRepository;
import com.shop.appshoplist.data.repository.InMemoryProductRepository;

public class AddProduct extends AppCompatActivity {
    private EditText edtProductName;
    private EditText edtProductPrice;
    private Button btnAgregar;
    private Button btnCancelar;
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
        this.edtProductName = findViewById(R.id.edtAgregarNombre);
        this.edtProductPrice = findViewById(R.id.edtAgregarPrecio);
        this.btnCancelar = findViewById(R.id.btnRegresar);
        this.iProductRepository = new InMemoryProductRepository();
        this.getBack = new Intent(this,MainActivity.class);
    }

    public void addProduct(){
        init();
        getBackToMainScreen();

        Product product = new Product();

        this.btnAgregar.setOnClickListener(v->{
            product.setName(String.valueOf(this.edtProductName.getText()));
            product.setPrice(Double.parseDouble(String.valueOf(this.edtProductPrice)));
            product.setChecked(false);

            iProductRepository.addProduct(product);
            startActivity(this.getBack);
        });
    }

    public void getBackToMainScreen(){
        this.btnCancelar.setOnClickListener(v -> startActivity(this.getBack));
    }
}