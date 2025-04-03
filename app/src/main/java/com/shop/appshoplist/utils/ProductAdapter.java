package com.shop.appshoplist.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.shop.appshoplist.R;
import com.shop.appshoplist.data.model.Product;
import com.shop.appshoplist.data.repository.IProductRepository;
import com.shop.appshoplist.data.repository.InMemoryProductRepository;

import java.security.PublicKey;
import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private static LayoutInflater inflater;
    private final List<Product> products;

    public ProductAdapter(Context context, List<Product> products){
        this.products = products;
        ProductAdapter.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_element, parent, false);

            viewHolder = new ViewHolder();
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
        

        return convertView;
    }

    static class ViewHolder {
        TextView txtNombreProducto;
        CheckBox isChecked;
        TextView txtPrecio;
        TextView txtEditar;
        TextView txtEliminar;
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
