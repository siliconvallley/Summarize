package com.dh.summarize.activity.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dh.summarize.R;
import com.dh.summarize.base.BaseActivity;
import com.google.android.material.appbar.MaterialToolbar;

public class NavigationActivity extends BaseActivity {

    private MaterialToolbar toolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_navigation;
    }

    @Override
    public void initViews() {
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void doBusiness() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_navigation) {

        }
        return super.onOptionsItemSelected(item);
    }

    /*@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }*/
}