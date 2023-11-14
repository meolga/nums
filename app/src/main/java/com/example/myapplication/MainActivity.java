package com.example.myapplication;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import io.github.hyuwah.draggableviewlib.DraggableListener;
import io.github.hyuwah.draggableviewlib.DraggableView;

public class MainActivity extends AppCompatActivity {

    private static final int SIZE = 10;
    private ActivityMainBinding binding;
    private Model model = new Model(SIZE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        for (int i = 0; i < SIZE; i++) {
            LinearLayout row = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.row, null);
            binding.main.addView(row);
            for (int j = 0; j < SIZE; j++) {
                FrameLayout text = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.text, null);
                ((TextView)text.findViewById(R.id.text)).setText("" + model.get(i, j));
                row.addView(text);
                text.setOnClickListener(v -> text.setSelected(true));
            }
        }
    }
}