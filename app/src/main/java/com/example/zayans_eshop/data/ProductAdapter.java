package com.example.zayans_eshop.data;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.zayans_eshop.R;

import java.util.ArrayList;
//this cls is to control the  list items showed in the listview from the arrylist
public class ProductAdapter extends ArrayAdapter<Product> {


    public  ProductAdapter(Activity context, ArrayList<Product> products) {

        super(context,0,products);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemview=convertView;
        Product currentProduct = getItem(position);

        if(listItemview==null){
            listItemview= LayoutInflater.from(getContext()).inflate(R.layout.product_list_item,parent,false);
        }
        TextView name = listItemview.findViewById(R.id.name);
        name.setText(currentProduct.getName());
        TextView price = listItemview.findViewById(R.id.price);
        price.setText(String.valueOf(currentProduct.getPrice()));
        TextView stock=listItemview.findViewById(R.id.stock);
        stock.setText(String.valueOf(currentProduct.getStock()));
        TextView image1=listItemview.findViewById(R.id.image1url);
        image1.setText(currentProduct.getImage1Url());
        TextView image2=listItemview.findViewById(R.id.image2url);
        image2.setText(currentProduct.getImage2Url());
        TextView image3=listItemview.findViewById(R.id.image3url);
        image3.setText(currentProduct.getImage3Url());
        return listItemview;

    }


}