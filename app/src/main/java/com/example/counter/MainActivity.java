package com.example.counter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int counter;
    Button plus;
    TextView countStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plus = (Button) findViewById(R.id.plus);
        countStr = (TextView) findViewById(R.id.display);
        sharedPreferences = getSharedPreferences("count", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        count();

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter != 0) {
                    counter = sharedPreferences.getInt("count", 0);
                    counter += 1;
                    countStr.setText(String.format("%04d", counter));
                } else {
                    counter += 1;
                    countStr.setText(String.format("%04d", counter));
                }
                commitToSharedPreferences();
            }
        });
    }

    public void commitToSharedPreferences(){
        editor.putInt("count",counter);
        editor.commit();
    }

    private void count() {
        sharedPreferences = getSharedPreferences("count", Context.MODE_PRIVATE);
        counter = sharedPreferences.getInt("count", 0);
        countStr.setText(String.format("%04d", counter));
    }
}