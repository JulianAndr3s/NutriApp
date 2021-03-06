package com.niceapp.nutriapp.nosotros;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.niceapp.nutriapp.R;
import com.niceapp.nutriapp.util.ViewUtil;

public class NosotrosActivity extends AppCompatActivity {

    private ViewUtil viewUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nosotros);
        viewUtil = new ViewUtil(this);
        viewUtil.setToolBar("Nosotros");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
