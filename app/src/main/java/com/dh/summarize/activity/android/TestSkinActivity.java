package com.dh.summarize.activity.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dh.static_skin.SkinActivity;
import com.dh.summarize.R;

public class TestSkinActivity extends SkinActivity implements View.OnClickListener {
    private Button btStaticSkin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_skin);
        btStaticSkin = findViewById(R.id.btStaticSkin);
        btStaticSkin.setOnClickListener(this);
    }

    @Override
    protected boolean openSkin() {
        return true;
    }

    @Override
    public void onClick(View v) {
        changeDayOrNight();
    }
}