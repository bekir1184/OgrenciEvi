package com.example.ogrencievi2.Sayfalar.FirebaseClaslar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.MenuPopupWindow;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

import com.example.ogrencievi2.R;

import org.w3c.dom.Text;

import java.util.List;

public class NotAdapter extends PagerAdapter {

    Activity context;
    List<Not> liste;
    private LayoutInflater layoutInflater;


    public NotAdapter(Activity context, List<Not> liste) {
        this.context = context;
        this.liste = liste;
    }
    public NotAdapter(){

    }

    @Override
    public int getCount() {
        return liste.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater =LayoutInflater.from(context);
        View view =layoutInflater.inflate(R.layout.notitem,container,false);

        TextView notBaslik,noticerik,ekleyenkisi;
        notBaslik=view.findViewById(R.id.notBaslikItem);
        noticerik=view.findViewById(R.id.icerik);
        ekleyenkisi=view.findViewById(R.id.ekleyenKisi);



        notBaslik.setText(liste.get(position).getBaslik());
        noticerik.setText(liste.get(position).getIcerik());
        ekleyenkisi.setText(liste.get(position).getEkleyenKisi());

        container.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
