package com.example.zayans_eshop.data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zayans_eshop.ProductDetails;
import com.example.zayans_eshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
//this cls is to control the  list items showed in the listview from the arrylist
public class ProductAdapter extends ArrayAdapter<Product> {

    private Context context;

    public  ProductAdapter(Activity context, ArrayList<Product> products) {
        super(context,0,products);
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemview=convertView;
        final Product currentProduct = getItem(position);

        if(listItemview==null){
            listItemview= LayoutInflater.from(getContext()).inflate(R.layout.product_list_item,parent,false);
        }

        // onClick listener for each product item in list
        LinearLayout product = listItemview.findViewById(R.id.currentProduct);
        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra("name", currentProduct.getName());
                intent.putExtra("price", currentProduct.getPrice());
                intent.putExtra("stock", currentProduct.getStock());
                intent.putExtra("description", currentProduct.getDescription());
                intent.putExtra("discPrice", currentProduct.getDiscountedPrice());
                intent.putExtra("im1", currentProduct.getImage1Url());
                intent.putExtra("im2", currentProduct.getImage2Url());
                intent.putExtra("im3", currentProduct.getImage3Url());

                context.startActivity(intent);
            }
        });

        TextView name = listItemview.findViewById(R.id.name);
        name.setText(currentProduct.getName());
        TextView price = listItemview.findViewById(R.id.price);
        price.setText("Regular Price: " + currentProduct.getPrice());
        TextView offerPrice = listItemview.findViewById(R.id.offerPrice);
        offerPrice.setText("Discounted Price: " + currentProduct.getDiscountedPrice());
        ImageView image1 = listItemview.findViewById(R.id.image1);
        if (currentProduct.getImage1Url() != "")
            Picasso.with(context).load(Uri.parse(currentProduct.getImage1Url())).into(image1);
        return listItemview;
    }


}