package com.example.ogrencievi2.QrKod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ogrencievi2.Login.EveGir;
import com.example.ogrencievi2.R;

public class QrKod extends AppCompatActivity {

    public static String kisiResim;
    Button tara;
    EditText isim;
    public static  String isimStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_kod);

        isim=findViewById(R.id.isimAlaniQR);


        tara=findViewById(R.id.tara);
        kisiResim="0";






        tara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 isimStr=isim.getText().toString();

                if(TextUtils.isEmpty(isimStr)){
                    Toast.makeText(QrKod.this,"İsim girmelisiniz", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(QrKod.this,Tara.class);
                    startActivity(intent);

                }


            }
        });

    }
}
