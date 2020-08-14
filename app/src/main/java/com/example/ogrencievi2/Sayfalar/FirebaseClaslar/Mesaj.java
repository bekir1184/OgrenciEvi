package com.example.ogrencievi2.Sayfalar.FirebaseClaslar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;


public class Mesaj extends AppCompatDialogFragment {
    String baslik;
    String icerik;
    public Mesaj(String baslik,String icerik){
        this.baslik=baslik;
        this.icerik=icerik;

    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setTitle(baslik)
                .setMessage(icerik)
                .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();
    }
}
