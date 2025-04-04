package com.shop.appshoplist.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.shop.appshoplist.MainActivity;
import com.shop.appshoplist.R;
import com.shop.appshoplist.UpdateProduct;
import com.shop.appshoplist.data.model.Product;
import com.shop.appshoplist.data.repository.IProductRepository;
import com.shop.appshoplist.data.repository.InMemoryProductRepository;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private static LayoutInflater inflater;
    private final List<Product> products;
    private final Context context;

    public ProductAdapter(Context context, List<Product> products){
        this.products = products;
        this.context = context;
        ProductAdapter.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_element, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.iProductRepository = new InMemoryProductRepository();

            viewHolder.txtNombreProducto = convertView.findViewById(R.id.txtNombreDelProductoCheck);
            viewHolder.isChecked = convertView.findViewById(R.id.cbxChecado);
            viewHolder.txtPrecio = convertView.findViewById(R.id.txtPrecioCheck);
            viewHolder.txtEditar = convertView.findViewById(R.id.txtEditar);
            viewHolder.txtEliminar = convertView.findViewById(R.id.txtEliminar);
            viewHolder.edtCantidad = convertView.findViewById(R.id.edtCantidadProducto);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Product dataProduct = products.get(position);

        viewHolder.txtNombreProducto.setText(dataProduct.getName());
        viewHolder.isChecked.setChecked(dataProduct.isChecked());
        viewHolder.txtPrecio.setText(String.valueOf(dataProduct.getPrice()));
        viewHolder.edtCantidad.setText(String.valueOf(dataProduct.getQuantity()));

        /**
         * Es fundamental para saber si esta comprado o no el producto
         * */
        viewHolder.isChecked.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Product product;
            if (isChecked) {
                product = products.get(position);
                product.setChecked(true);
            } else {
                product = products.get(position);
                product.setChecked(false);
            }
            viewHolder.iProductRepository.modifyProduct(position, product);
        });

        /**
         * Boton de editar, redirecciona a la pantalla de editar
         * */
        viewHolder.txtEditar.setOnClickListener(v -> {
                Intent screenUpdate = new Intent(this.context, UpdateProduct.class);
                screenUpdate.putExtra("index", position);
                this.context.startActivity(screenUpdate);
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
            }
        );

        /**
         * Manejo de la eliminacion de un elemento
         * */
        viewHolder.txtEliminar.setOnClickListener(v ->{
            mostrarDialogoConfirmacion(context, () -> {
                Product product = products.get(position);
                viewHolder.iProductRepository.deleteProduct(product);
                notifyDataSetChanged();
            });
        });

        /**
         * Manejo del cambio del numero
         * */
         viewHolder.edtCantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            /**
             * Se ejecuta cuando se cambia el texto del EditText
             * */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    Product product = products.get(position);
                    product.setQuantity(Integer.parseInt(String.valueOf(viewHolder.edtCantidad.getText())));
                    viewHolder.iProductRepository.modifyProduct(position, product);
                }
            }
        });

        viewHolder.edtCantidad.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (viewHolder.edtCantidad.getText().toString().isEmpty()) {
                    Product product = products.get(position);
                    product.setQuantity(1);
                    viewHolder.edtCantidad.setText("1");
                    viewHolder.iProductRepository.modifyProduct(position, product);
                }
            }
        });

        Log.d("Debug", products.get(position).toString());
        return convertView;
    }

    private void mostrarDialogoConfirmacion(Context context, Runnable onConfirmar) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Seguro que quieres eliminar este elemento?");

        // Botón de Cancelar
        builder.setNegativeButton("Cancelar", (dialog, which) -> {
            dialog.dismiss(); // Cierra el diálogo sin hacer nada
        });

        // Botón de Borrar
        builder.setPositiveButton("Borrar", (dialog, which) -> {
            onConfirmar.run(); // Ejecuta la acción de borrado
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Esta clase estatica es de gran utilidad para evitar sobrecarga de memoria.
     * Evita que se tenga que reasignar los elementos con su id, reduce la memoria usada
     * */
    static class ViewHolder {
        TextView txtNombreProducto;
        CheckBox isChecked;
        TextView txtPrecio;
        TextView txtEditar;
        TextView txtEliminar;
        EditText edtCantidad;
        IProductRepository iProductRepository;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
