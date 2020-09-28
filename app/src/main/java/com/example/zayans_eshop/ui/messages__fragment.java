package com.example.zayans_eshop.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.zayans_eshop.R;

public class messages__fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.messages_fragment,container,false);
        Button phoneContact = root.findViewById(R.id.phoneContact);
        Button emailContact = root.findViewById(R.id.emailContact);
        Button faq = root.findViewById(R.id.faq);
        phoneContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "+8801997864687";
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
                        "mailto", "zayansmegashop@gmail.com", null
                ));
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, text);

                startActivity(Intent.createChooser(intent, "Choose an email app"));
            }
        });
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),FAQactivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

}
