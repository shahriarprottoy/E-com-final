package com.zayan.eshop.data;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.zayan.eshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterOrderProduct extends ArrayAdapter<Product> {

    private Context context;

    public AdapterOrderProduct(Activity context, ArrayList<Product> products) {
        super(context, 0, products);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemview = convertView;
        final Product currentProduct = getItem(position);

        if (listItemview == null) {
            listItemview = LayoutInflater.from(getContext()).inflate(R.layout.order_list_item, parent, false);
        }

        final CardView product = listItemview.findViewById(R.id.currentOrderProduct);

        TextView name = listItemview.findViewById(R.id.name);
        name.setText(currentProduct.getName());
        TextView quantity = listItemview.findViewById(R.id.quantity);
        quantity.setText("Products: " + currentProduct.getQuantity());
        TextView offerPrice = listItemview.findViewById(R.id.offerPrice);
        offerPrice.setText("Total of this: TK. " + currentProduct.getTotalCost());
        ImageView image1 = listItemview.findViewById(R.id.image1);
        if (!currentProduct.getImage1Url().equals(""))
            Picasso.with(context).load(Uri.parse(currentProduct.getImage1Url())).into(image1);
        else
            image1.setImageResource(R.drawable.ic_shopping_basket);
        return product;
    }

}