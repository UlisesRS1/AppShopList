package com.shop.appshoplist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.shop.appshoplist.data.model.Product;
import com.shop.appshoplist.data.repository.IProductRepository;
import com.shop.appshoplist.data.repository.InMemoryProductRepository;

import java.util.List;

public class UpdateProduct extends AppCompatActivity {

    private Button btnModificar;
    private Button btnCancelar;
    private EditText edtUpdateName;
    private EditText edtupdatePrice;
    private Intent getBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        runActivity();
    }

    public void runActivity(){
        init();
        update();
        getBackToMainScreen();
    }

    public void init(){
        this.btnModificar = findViewById(R.id.btnModificar);
        this.edtUpdateName = findViewById(R.id.edtModificarProducto);
        this.edtupdatePrice = findViewById(R.id.edtndModificarValor);
        this.btnCancelar = findViewById(R.id.btnCancelar);

        this.getBack = new Intent(this, MainActivity.class);
    }

    public void setTextInEditText(int index){
        IProductRepository iProductRepository = new InMemoryProductRepository();
        var products = iProductRepository.getAllProducts();

        this.edtUpdateName.setText(products.get(index).getName());
        this.edtupdatePrice.setText(String.valueOf(products.get(index).getPrice()));
    }

    public void update(){
        init();
        int index = getIntent().getIntExtra("index", 0);
        setTextInEditText(index);

        IProductRepository iProductRepository = new InMemoryProductRepository();

        Product newProduct = new Product();

        this.btnModificar.setOnClickListener(v -> {
            newProduct.setName(String.valueOf(this.edtUpdateName.getText()));
            newProduct.setPrice(Double.parseDouble(String.valueOf(this.edtupdatePrice.getText())));
            newProduct.setChecked(false);

            iProductRepository.modifyProduct(index, newProduct);
            startActivity(this.getBack);
            finish();
        });
    }

    public void getBackToMainScreen(){
        this.btnCancelar.setOnClickListener(v -> {
            startActivity(this.getBack);
            finish();
        });
    }

}