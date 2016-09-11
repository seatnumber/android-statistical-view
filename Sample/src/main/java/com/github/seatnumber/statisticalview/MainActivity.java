package com.github.seatnumber.statisticalview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.arcTextView)
    public void showArc(){
        ArcViewFragment arcViewFragment = new ArcViewFragment();
        arcViewFragment.show(getSupportFragmentManager(),"");
    }
}
