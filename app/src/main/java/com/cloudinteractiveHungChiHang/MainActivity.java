package com.cloudinteractiveHungChiHang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cloudinteractiveHungChiHang.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {
    private MainPresenter presenter;
    private Button callApiBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);
        setFindViewById();
        setClickListener();
    }

    private void setClickListener() {
        callApiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.RequestApi();
            }
        });
    }

    private void setFindViewById() {
        callApiBtn = findViewById(R.id.callApiBtn);
    }

    @Override
    public void goToListPage() {
        Intent intent = new Intent(MainActivity.this,ListActivity.class);
        startActivity(intent);
    }
}