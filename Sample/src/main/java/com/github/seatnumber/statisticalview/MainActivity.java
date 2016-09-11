package com.github.seatnumber.statisticalview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.seatnumber.library.view.HistogramView;

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

    @OnClick(R.id.histogramView)
    public void showHistogram(){
        HistogramViewFragment histogramViewFragment = new HistogramViewFragment();
        histogramViewFragment.show(getSupportFragmentManager(),"");
    }
}
