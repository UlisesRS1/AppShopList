package com.shop.appshoplist.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shop.appshoplist.R;
import com.shop.appshoplist.data.model.Product;
import com.shop.appshoplist.data.repository.IProductRepository;
import com.shop.appshoplist.data.repository.InMemoryProductRepository;

import java.util.List;

public class ProductAdapterCheckList extends BaseAdapter {

    private static LayoutInflater inflater;
    private final List<Product> products;
    private final Context context;

    public ProductAdapterCheckList(Context context, List<Product> products){
        this.products = products;
        this.context = context;
        ProductAdapterCheckList.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_element_check, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.iProductRepository = new InMemoryProductRepository();

            viewHolder.txtNombreProducto = convertView.findViewById(R.id.txtNombreDelProductoCheck);
            viewHolder.txtPrecio = convertView.findViewById(R.id.txtPrecioCheck);
            viewHolder.edtCantidad = convertView.findViewById(R.id.txtCantidadProductoCheck);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Product dataProduct = products.get(position);

        viewHolder.txtNombreProducto.setText(dataProduct.getName());
        viewHolder.txtPrecio.setText(String.valueOf(dataProduct.getPrice()));
        viewHolder.edtCantidad.setText(String.valueOf(dataProduct.getQuantity()));

        return convertView;
    }

    /**
     * Esta clase estatica es de gran utilidad para evitar sobrecarga de memoria.
     * Evita que se tenga que reasignar los elementos con su id, reduce la memoria usada
     * */
    static class ViewHolder {
        TextView txtNombreProducto;
        TextView txtPrecio;
        TextView edtCantidad;
        IProductRepository iProductRepository;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
