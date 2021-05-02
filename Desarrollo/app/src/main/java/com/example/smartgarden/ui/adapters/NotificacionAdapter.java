package com.example.smartgarden.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartgarden.R;
import com.example.smartgarden.ui.entities.Notificacion;
import java.util.ArrayList;

public class NotificacionAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Notificacion> items;

    public NotificacionAdapter(Activity activity, ArrayList<Notificacion> items){
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() { return items.size(); }

    @Override
    public Notificacion getItem(int position) { return items.get(position); }

    @Override
    public long getItemId(int position) { return items.get(position).getNotiID(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Notificacion item = items.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_notificacion, null);
        }
        ImageView img = convertView.findViewById(R.id.ImgItemNoti);
        TextView txtTitulo = convertView.findViewById(R.id.TxtItemNoti);

        img.setImageResource(item.getImg());
        txtTitulo.setText(item.getTitulo());

        return convertView;
    }
}
