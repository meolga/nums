package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;

import com.example.myapplication.databinding.ActivityMainBinding;

import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    private static final int SIZE = 8;
    public static final String LEFT = "left";
    public static final String UP = "up";
    public static final String RIGHT = "right";
    public static final String DOWN = "down";
    private ActivityMainBinding binding;
    private Model model = new Model(SIZE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final LinearLayout upRow = addRow();
        addButtonInvisible(upRow);
        IntStream.range(0, SIZE).forEach(j -> {
            FrameLayout button = addButton(upRow, UP);
            button.setOnClickListener(v -> model.up(j));
        });
        addButtonInvisible(upRow);

        IntStream.range(0, SIZE).forEach(i -> {
            LinearLayout r = addRow();
            addButton(r, LEFT).setOnClickListener(v -> model.left(i));
            IntStream.range(0, SIZE).forEach(j -> {
                FrameLayout button = addButton(r, "" + model.get(i, j));
                button.setOnClickListener(v -> button.setSelected(!button.isSelected()));
                model.addListener(i, j, (i1, j1, value) -> ((TextView) button.findViewById(R.id.text)).setText("" + value));
            });
            addButton(r, RIGHT).setOnClickListener(v -> model.right(i));;
        });


        final LinearLayout downRow = addRow();
        addButtonInvisible(downRow);
        IntStream.range(0, SIZE).forEach(j -> {
            FrameLayout button = addButton(downRow, DOWN);
            button.setOnClickListener(v -> model.down(j));
        });
        addButtonInvisible(downRow);

    }

    private LinearLayout addRow() {
        LinearLayout row = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.row, null);
        binding.main.addView(row);
        return row;
    }

    private FrameLayout addButton(LinearLayout row, String text) {
        FrameLayout layout = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.text, null);
        TextView textView = layout.findViewById(R.id.text);
        textView.setText(text);

        row.addView(layout);
        return layout;
    }

    private void addButtonInvisible(LinearLayout row) {
        FrameLayout layout = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.text, null);
        layout.setVisibility(View.INVISIBLE);

        row.addView(layout);
    }
}