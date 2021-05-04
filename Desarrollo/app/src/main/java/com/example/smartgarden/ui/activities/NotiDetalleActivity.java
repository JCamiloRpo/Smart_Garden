package com.example.smartgarden.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartgarden.R;
import com.example.smartgarden.ui.entities.Notificacion;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class NotiDetalleActivity extends AppCompatActivity {
    static ArrayList<Notificacion> noti;
    static int pos;
    Notificacion current;
    int[] imgs;
    ImageButton btnVolver;
    TextView txtTitulo, txtDescripcion;
    CarouselView crlImagenes;
    Button btnAtras, btnSiguiente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notidetalle);

        setUpView();
    }

    public static void setNoti(ArrayList<Notificacion> Noti){ noti = Noti; }
    public static void setPos(int Pos){ pos = Pos; }

    private void setUpView(){
        btnVolver = findViewById(R.id.BtnVolver);
        txtTitulo = findViewById(R.id.TxtNotiTitulo);
        crlImagenes = findViewById(R.id.CrlNotiImgs);
        txtDescripcion = findViewById(R.id.TxtNotiDescripcion);
        btnAtras = findViewById(R.id.BtnAtras);
        btnSiguiente = findViewById(R.id.BtnSiguiente);

        current = noti.get(pos);
        txtTitulo.setText(current.getTitulo());
        txtDescripcion.setText(current.getDescripcion());
        cargarImgs();
        cargarBotones();
    }

    private void cargarImgs(){
        int tipo = current.getTipo();
        // 1-Abonar, 2-Analisis, 3-Cosechar, 4-Podar, 5-Regar
        switch (tipo){
            case 1:
                imgs = new int[]{R.drawable.notidetalle_abonar1, R.drawable.notidetalle_abonar2, R.drawable.notidetalle_abonar3};
                break;
            case 2:
                imgs = new int[]{R.drawable.notidetalle_analisis1, R.drawable.notidetalle_analisis2, R.drawable.notidetalle_analisis3};
                break;
            case 3:
                imgs = new int[]{R.drawable.notidetalle_cosechar1, R.drawable.notidetalle_cosechar2, R.drawable.notidetalle_cosechar3};
                break;
            case 4:
                imgs = new int[]{R.drawable.notidetalle_podar1, R.drawable.notidetalle_podar2, R.drawable.notidetalle_regar3};
                break;
            case 5:
                imgs = new int[]{R.drawable.notidetalle_regar1, R.drawable.notidetalle_regar2, R.drawable.notidetalle_regar3};
                break;
            default:
                imgs = new int[]{R.drawable.notfound};
                break;
        }
        crlImagenes.setPageCount(imgs.length);
        crlImagenes.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(imgs[position]);
            }
        });
    }

    private void cargarBotones(){
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (pos == 0){
            btnAtras.setEnabled(false);
            btnAtras.setVisibility(View.INVISIBLE);
        }
        else{
            btnAtras.setEnabled(true);
            btnAtras.setVisibility(View.VISIBLE);
        }
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos > 0){
                    pos--;
                    setUpView();
                }
            }
        });

        if (pos == noti.size()-1){
            btnSiguiente.setEnabled(false);
            btnSiguiente.setVisibility(View.INVISIBLE);
        }
        else{
            btnSiguiente.setEnabled(true);
            btnSiguiente.setVisibility(View.VISIBLE);
        }
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos < noti.size()-1){
                    pos++;
                    setUpView();
                }
            }
        });
    }
}