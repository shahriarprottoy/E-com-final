package com.example.zayans_eshop.data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.PopupMenu;

import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;

import com.example.zayans_eshop.MainActivity;
import com.example.zayans_eshop.ProductDetails;
import com.example.zayans_eshop.R;
import com.example.zayans_eshop.ui.cart__fragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterCartProduct extends ArrayAdapter<Product> {

    private Context context;

    public AdapterCartProduct(Activity context, ArrayList<Product> products) {
        super(context, 0, products);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemview = convertView;
        final Product currentProduct = getItem(position);

        if (listItemview == null) {
            listItemview = LayoutInflater.from(getContext()).inflate(R.layout.cart_list_item, parent, false);
        }

        // onClick listener for each product item in list
        final CardView product = listItemview.findViewById(R.id.currentCartProduct);
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
                intent.putExtra("deliverycost", currentProduct.getDeliveryCost());
                intent.putExtra("setupcost", currentProduct.getSetupCost());
                intent.putExtra("deliverytaken",currentProduct.isDeliveryTaken());
                intent.putExtra("setuptaken",currentProduct.isSetupTaken());
                intent.putExtra("id", currentProduct.getId());
                intent.putExtra("fromCart", true);

                context.startActivity(intent);
            }
        });

        final TextView optionsMenuButton = product.findViewById(R.id.textViewOptions);
        SwitchCompat delivery_switch = product.findViewById(R.id.delivery_switch);
        final SwitchCompat setup_switch = product.findViewById(R.id.setup_switch);

        optionsMenuButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(context, optionsMenuButton);
                popup.inflate(R.menu.cart_item_options_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.remove) {
                            MainActivity.cartProducts.remove(currentProduct);
                            notifyDataSetChanged();
                            assert currentProduct != null;
                            cart__fragment.RefreshTotal();
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });

        final EditText editText = product.findViewById(R.id.numberOfProducts);
        Button increment = product.findViewById(R.id.incrementButton);
        Button decrement = product.findViewById(R.id.decrementButton);

        assert currentProduct != null;
        if (currentProduct.getSetupCost() == 0 || !currentProduct.isDeliveryTaken()) {
            setup_switch.setEnabled(false);
            setup_switch.setChecked(false);
        } else {
            setup_switch.setEnabled(true);
            if(currentProduct.isSetupTaken())
                setup_switch.setChecked(true);
        }
        if(currentProduct.isDeliveryTaken()){
            delivery_switch.setChecked(true);
        }

        editText.setText(String.valueOf(currentProduct.getQuantity()));

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(editText.getText().toString()) < currentProduct.getStock()) {
                    currentProduct.setQuantity(currentProduct.getQuantity()+1);
                    editText.setText(String.valueOf(currentProduct.getQuantity()));
                    cart__fragment.RefreshTotal();
                } else {
                    Toast.makeText(context, "Out of stock", Toast.LENGTH_SHORT).show();
                }

            }
        });

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(Integer.parseInt(editText.getText().toString()) <= 1)) {
                    currentProduct.setQuantity(currentProduct.getQuantity()-1);
                    editText.setText(String.valueOf(currentProduct.getQuantity()));
                    cart__fragment.RefreshTotal();
                    }
            }
        });

        delivery_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    currentProduct.setDeliveryTaken(true);
                    if(currentProduct.getSetupCost() != 0) {
                        setup_switch.setEnabled(true);
                        setup_switch.setChecked(true);
                    }
                } else {
                    currentProduct.setDeliveryTaken(false);
                    setup_switch.setEnabled(false);
                    setup_switch.setChecked(false);
                    currentProduct.setSetupTaken(false);
                }
                cart__fragment.RefreshTotal();
            }
        });

        setup_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if(currentProduct.getSetupCost() != 0)
                        currentProduct.setSetupTaken(true);
                } else {
                    currentProduct.setSetupTaken(false);
                }
                cart__fragment.RefreshTotal();
            }
        });

        TextView name = listItemview.findViewById(R.id.name);
        name.setText(currentProduct.getName());
        TextView price = listItemview.findViewById(R.id.price);
        price.setPaintFlags(price.getPaintFlags() | TextPaint.STRIKE_THRU_TEXT_FLAG);
        price.setText("TK. " + currentProduct.getPrice());
        TextView offerPrice = listItemview.findViewById(R.id.offerPrice);
        offerPrice.setText("TK. " + currentProduct.getDiscountedPrice());
        ImageView image1 = listItemview.findViewById(R.id.image1);
        if (!currentProduct.getImage1Url().equals(""))
            Picasso.with(context).load(Uri.parse(currentProduct.getImage1Url())).into(image1);
        else
            image1.setImageResource(R.drawable.ic_shopping_basket);
        return product;
    }

}