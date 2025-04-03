package com.shop.appshoplist.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;

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
        final View vista = ProductAdapter.inflater.inflate(R.layout.list_element, null);



        return null;
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
