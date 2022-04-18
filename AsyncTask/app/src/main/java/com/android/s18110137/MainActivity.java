package com.android.s18110137;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.s18110137.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private MyWorkTask myWorkTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        this.binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWork();
            }
        });
    }

    private void startWork() {
        this.myWorkTask = new MyWorkTask(binding);

        ParamInfo param = new ParamInfo("Param 1", "Param 2");

        this.myWorkTask.execute(param);
    }
}