package com.example.smartgarden.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartgarden.R;
import com.example.smartgarden.ui.activities.NotiDetalleActivity;
import com.example.smartgarden.ui.entities.Analisis;
import com.example.smartgarden.ui.entities.Notificacion;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.renderer.XAxisRenderer;

import java.util.ArrayList;

public class AnalisisAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Analisis> items;

    public AnalisisAdapter(Activity activity, ArrayList<Analisis> items){
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() { return items.size(); }

    @Override
    public Analisis getItem(int position) { return items.get(position); }

    @Override
    public long getItemId(int position) { return items.get(position).getAnalisisID(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Analisis item = items.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_analisis, null);
        }
        TextView txtTitulo = convertView.findViewById(R.id.TxtItemAnalisis);
        LineChart chtGrafica = convertView.findViewById(R.id.ChtItemLine);

        txtTitulo.setText(item.getTitulo());
        chtGrafica.setData(item.getGrafica());

        chtGrafica.setNoDataText("No se encontraron datos");
        chtGrafica.setScaleEnabled(false);
        chtGrafica.setTouchEnabled(false);
        chtGrafica.setDescription(null);
        XAxis xAxis = chtGrafica.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);
        YAxis[] yAxis = {chtGrafica.getAxisLeft(), chtGrafica.getAxisRight()};
        yAxis[0].setDrawAxisLine(false);
        yAxis[0].setDrawGridLines(false);
        yAxis[0].setDrawLabels(false);
        yAxis[1].setDrawAxisLine(false);
        yAxis[1].setDrawGridLines(false);
        yAxis[1].setDrawLabels(false);
        Legend legend = chtGrafica.getLegend();
        legend.setEnabled(false);

        chtGrafica.animateY(2000, Easing.EaseInOutCubic);
        chtGrafica.invalidate();


        return convertView;
    }
}
