package com.example.smartgarden.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.example.smartgarden.R;
import com.example.smartgarden.ui.entities.Analisis;
import com.example.smartgarden.ui.fragments.AnalisisDetalleFragment;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

import java.util.ArrayList;

public class AnalisisAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Analisis> items;
    LineChart chtGrafica;

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
        chtGrafica = convertView.findViewById(R.id.ChtItemLine);

        txtTitulo.setText(item.getTitulo());
        chtGrafica.setData(item.getLineData());
        configurarGrafica(item.getAnalisisID());

        txtTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnalisisDetalleFragment.analisis = item;

                Navigation.findNavController(v).navigate(R.id.action_analisis_to_detalle);
            }
        });

        return convertView;
    }

    private void configurarGrafica(long scale){
        chtGrafica.setNoDataText("No se encontraron datos");
        chtGrafica.setDoubleTapToZoomEnabled(false);
        chtGrafica.setTouchEnabled(true);
        chtGrafica.zoomToCenter(scale/10,0);
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
        legend.setEnabled(true);

        chtGrafica.animateY(1000, Easing.EaseInOutCubic);
        chtGrafica.invalidate();
    }
}
