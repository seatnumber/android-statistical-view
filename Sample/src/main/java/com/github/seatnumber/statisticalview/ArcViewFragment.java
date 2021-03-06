package com.github.seatnumber.statisticalview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.seatnumber.library.view.ArcView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by seatNumber on 16/9/11.
 */

public class ArcViewFragment extends DialogFragment {

    @InjectView(R.id.arcView)
    ArcView arcView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_arcview, null);
        ButterKnife.inject(this, root);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        arcView.setCounts(new float[]{40,30,20,10});
        arcView.startAnim();
    }
}
