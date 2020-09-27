package com.example.zayans_eshop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.zayans_eshop.data.AdapterViewPager;
import com.example.zayans_eshop.data.Product;


public class ProductDetails extends AppCompatActivity {

    // This is the product to show details of
    private Product product = new Product();
    private boolean fromCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        /*Button back = findViewById(R.id.header_back_button);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/

        Intent intent = getIntent();

        // Setting product data from passed data
        product.setName(intent.getStringExtra("name"));
        product.setPrice(intent.getIntExtra("price", 0));
        product.setStock(intent.getIntExtra("stock", 0));
        product.setDiscountedPrice(intent.getIntExtra("discPrice", 0));
        product.setDescription(intent.getStringExtra("description"));
        product.setImage1Url(intent.getStringExtra("im1"));
        product.setImage2Url(intent.getStringExtra("im2"));
        product.setImage3Url(intent.getStringExtra("im3"));
        product.setDeliveryCost(intent.getIntExtra("deliverycost",0));
        product.setSetupCost(intent.getIntExtra("setupcost",0));
        product.setDeliveryTaken(intent.getBooleanExtra("deliverytaken",true));
        product.setSetupTaken(intent.getBooleanExtra("setuptaken",false));
        product.setId(intent.getIntExtra("id",0));
        product.setQuantity(1);
        fromCart = intent.getBooleanExtra("fromCart", false);

        ViewGroup setupCostLayout = findViewById(R.id.setup_cost_layout);
        if(product.getSetupCost() == 0)
            setupCostLayout.setVisibility(View.INVISIBLE);

        ViewPager viewPager = findViewById(R.id.viewPager);
        AdapterViewPager adapter = new AdapterViewPager(this, product.getImageUrls());
        viewPager.setAdapter(adapter);

        Button addToCartButton = findViewById(R.id.addtocart);
        if (fromCart || MainActivity.cartProducts.contains(product)) {
            addToCartButton.setVisibility(View.INVISIBLE);
        }
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.userAccount.getUserName() == null) {
                    Intent intent = new Intent(ProductDetails.this, Unsigned_screen.class);
                    startActivity(intent);
                } else {
                    boolean existsFlag = false;
                    boolean outOfStock = false;
                    if (MainActivity.cartProducts.size() > 0) {
                        for (int i = 0; i < MainActivity.cartProducts.size(); i++) {
                            if (MainActivity.cartProducts.get(i).getName().equals(product.getName())) {
                                existsFlag = true;
                                break;
                            }
                            if(product.getStock() == 0){
                                outOfStock = true;
                                break;
                            }
                        }
                        if (!existsFlag && !outOfStock) {
                            Toast.makeText(ProductDetails.this, "Product added to cart", Toast.LENGTH_SHORT).show();
                            MainActivity.cartProducts.add(product);
                        } else if(outOfStock){
                            Toast.makeText(ProductDetails.this, "Sorry, product out of stock", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(ProductDetails.this, "Product already added to cart", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ProductDetails.this, "Product added to cart", Toast.LENGTH_SHORT).show();
                        MainActivity.cartProducts.add(product);
                    }
                }
            }
        });

        TextView name = findViewById(R.id.name);
        name.setText(product.getName());
        TextView price = findViewById(R.id.price);
        price.setPaintFlags(price.getPaintFlags() | TextPaint.STRIKE_THRU_TEXT_FLAG);
        price.setText("Tk." + product.getPrice());
        TextView offerPrice = findViewById(R.id.offerPrice);
        offerPrice.setText("Tk." + product.getDiscountedPrice());
        TextView details = findViewById(R.id.details);
        details.setText("" + product.getDescription());
        TextView deliverycost=findViewById(R.id.deliverycost);
        deliverycost.setText(String.valueOf(product.getDeliveryCost()));
        TextView setupcost=findViewById(R.id.setupcost);
        setupcost.setText(String.valueOf(product.getSetupCost()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

