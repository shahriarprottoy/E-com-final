package com.example.zayans_eshop.data;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.graphics.Paint;

import com.example.zayans_eshop.ProductDetails;
import com.example.zayans_eshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterProduct extends ArrayAdapter<Product> {

    private Context context;

    public AdapterProduct(Activity context, ArrayList<Product> products) {
        super(context,0,products);
        this.context = context;
    }
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemview=convertView;
        final Product currentProduct = getItem(position);


        if(listItemview==null){
            listItemview= LayoutInflater.from(getContext()).inflate(R.layout.product_list_item,parent,false);
        }


        final TextView name = listItemview.findViewById(R.id.name);
        assert currentProduct != null;
        name.setText(currentProduct.getName());
        final TextView price = listItemview.findViewById(R.id.price);
        price.setText("TK. " + currentProduct.getPrice());
        price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        TextView offerPrice = listItemview.findViewById(R.id.offerPrice);
        offerPrice.setText("TK. " + currentProduct.getDiscountedPrice());
        final ImageView image1 = listItemview.findViewById(R.id.image1);
        if (!currentProduct.getImage1Url().equals(""))
            Picasso.with(context).load(Uri.parse(currentProduct.getImage1Url())).into(image1);
        else
            image1.setImageResource(R.drawable.ic_shopping_basket);

        // onClick listener for each product item in list
        final LinearLayout product = listItemview.findViewById(R.id.currentProduct);
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
                intent.putExtra("deliverycost",currentProduct.getDeliveryCost());
                intent.putExtra("setupcost",currentProduct.getSetupCost());
                intent.putExtra("id", currentProduct.getId());
                intent.putExtra("fromCart", false);
                Pair[] pairs=new Pair[4];
                pairs[0]=new Pair<View, String>(image1,"imageTransition");
                pairs[1]=new Pair<View,String >(name,"nameTransition");
                pairs[2]=new Pair<View,String >(price,"priceTransition");
                pairs[3]=new Pair<View,String >(name,"offerTransition");
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity) context,pairs);
                context.startActivity(intent,options.toBundle());
            }
        });
        return listItemview;
    }


}