package com.example.zayans_eshop;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.zayans_eshop.data.Product;

import java.util.ArrayList;
//this cls is to control the  list items showed in the listview from the arrylist
public class ProductAdapter extends ArrayAdapter<Product> {


    public  ProductAdapter(Activity context, ArrayList<Product> products) {

        super(context,0,products);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemview=convertView;
        if(listItemview==null){
            listItemview= LayoutInflater.from(getContext()).inflate(R.layout.product_list_item,parent,false);
        }
        Product currentProduct=  getItem(position);
        TextView name=(TextView)listItemview.findViewById(R.id.name);
        name.setText(currentProduct.getName());
        TextView price=(TextView)listItemview.findViewById(R.id.price);
        price.setText(currentProduct.getPrice());
        TextView stock=listItemview.findViewById(R.id.stock);
        stock.setText(currentProduct.getStock());
        TextView image1=listItemview.findViewById(R.id.image1url);
        image1.setText(currentProduct.getStock());
        TextView image2=listItemview.findViewById(R.id.image2url);
        image2.setText(currentProduct.getStock());
        TextView image3=listItemview.findViewById(R.id.image3url);
        image3.setText(currentProduct.getStock());
        return listItemview;

    }


}