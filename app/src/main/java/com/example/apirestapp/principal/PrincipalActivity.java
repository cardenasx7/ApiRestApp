package com.example.apirestapp.principal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apirestapp.R;
import com.example.apirestapp.crud.CrudActivity;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_STATUS ="status";

    Button btnPhp, btnJava, btnPython, btnNodeJs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_principal);
        btnPhp = findViewById(R.id.btnPhpLumen);
        btnJava = findViewById(R.id.btnJavaSpringBoot);
        btnPython = findViewById(R.id.btnPythonFlask);
        btnNodeJs = findViewById(R.id.btnNodejsExpress);
        btnPhp.setOnClickListener(this);
        btnJava.setOnClickListener(this);
        btnPython.setOnClickListener(this);
        btnNodeJs.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnPhpLumen:
                initStartActivityPhp();
                break;
            case R.id.btnJavaSpringBoot:
                initStartActivitySprint();
                break;
            case R.id.btnPythonFlask:
                initStartActivityPython();
                break;
            case R.id.btnNodejsExpress:
                initStartActivityNode();
                break;
        }
    }

    private void initStartActivityNode() {
        Intent intent =new Intent(this, CrudActivity.class);
        intent.putExtra(EXTRA_STATUS,"Node");
        startActivity(intent);
    }

    private void initStartActivityPython() {
        Intent intent =new Intent(this, CrudActivity.class);
        intent.putExtra(EXTRA_STATUS,"Python");
        startActivity(intent);
    }

    private void initStartActivitySprint() {
        Intent intent =new Intent(this, CrudActivity.class);
        intent.putExtra(EXTRA_STATUS,"Sprint");
        startActivity(intent);
    }

    private void initStartActivityPhp() {
        Intent intent =new Intent(this, CrudActivity.class);
        intent.putExtra(EXTRA_STATUS,"Php");
        startActivity(intent);
    }
}
