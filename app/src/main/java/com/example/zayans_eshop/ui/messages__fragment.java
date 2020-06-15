package com.example.zayans_eshop.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zayans_eshop.R;

public class messages__fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.messages_fragment,container,false);
        Button phoneContact = root.findViewById(R.id.phoneContact);
        Button emailContact = root.findViewById(R.id.emailContact);

        phoneContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "+8801798355165";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(
                        "tel", phone, null
                ));
                startActivity(intent);
            }
        });

        emailContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = "Product query";
                String text = "I would like to know";

                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "saimulislam2002@gmail.com", null
                ));
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, text);

                startActivity(Intent.createChooser(intent, "Choose an email app"));
            }
        });

        return root;
    }
}
