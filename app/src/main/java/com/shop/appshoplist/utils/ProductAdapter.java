package com.shop.appshoplist.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.shop.appshoplist.R;
import com.shop.appshoplist.ScreenPurchased;
import com.shop.appshoplist.UpdateProduct;
import com.shop.appshoplist.data.model.Product;
import com.shop.appshoplist.data.repository.IProductRepository;
import com.shop.appshoplist.data.repository.InMemoryProductRepository;

import java.security.PublicKey;
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

            viewHolder.txtNombreProducto = convertView.findViewById(R.id.txtNombreDelProducto);
            viewHolder.isChecked = convertView.findViewById(R.id.cbxChecado);
            viewHolder.txtPrecio = convertView.findViewById(R.id.textView7);
            viewHolder.txtEditar = convertView.findViewById(R.id.txtEditar);
            viewHolder.txtEliminar = convertView.findViewById(R.id.txtEliminar);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Product dataProduct = products.get(position);

        viewHolder.txtNombreProducto.setText(dataProduct.getName());
        viewHolder.isChecked.setChecked(dataProduct.isChecked());
        viewHolder.txtPrecio.setText(String.valueOf(dataProduct.getPrice()));

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
            Log.d("MiTag", this.products.get(position).toString());
        });

        // Estructura para la creación del evento de clic para nueva actividad
        viewHolder.txtEditar.setOnClickListener(v -> {
                Intent screenUpdate = new Intent(this.context, UpdateProduct.class);
                screenUpdate.putExtra("index", position);
                this.context.startActivity(screenUpdate);
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
            }
        );

        viewHolder.txtEliminar.setOnClickListener(v ->{
            mostrarDialogoConfirmacion(context, () -> {
                Product product = products.get(position);
                viewHolder.iProductRepository.deleteProduct(product);
                notifyDataSetChanged();
            });
        });


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
