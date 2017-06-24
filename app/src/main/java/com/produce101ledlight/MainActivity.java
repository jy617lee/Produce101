package com.produce101ledlight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.color_pallete)       RecyclerView mColorPallete;
    @BindView(R.id.layout_settings)     RelativeLayout mLayoutSettings;
    @BindView(R.id.speed_txt)           TextView speedTextView;

    @OnClick(R.id.ic_settings)
    public void showSettings(){
        if(mLayoutSettings.getVisibility() == View.VISIBLE){
            mLayoutSettings.setVisibility(View.INVISIBLE);
        }else{
            mLayoutSettings.setVisibility(View.VISIBLE);
        }
    }
    @OnClick(R.id.speed_up)
    public void speedUp(){
        int cur = Integer.parseInt(speedTextView.getText().toString());
        if(cur < 5) {
            speedTextView.setText(cur + 1 + "");
        }
    }
    @OnClick(R.id.speed_down)
    public void speedDown(){
        int cur = Integer.parseInt(speedTextView.getText().toString());
        if(cur > 0) {
            speedTextView.setText(cur - 1 + "");
        }
    }

    private RecyclerView.Adapter mColorAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int colorDataSet[] = {
            R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark,
            R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark,
            R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setColorPallete();
    }

    public void setColorPallete(){
        mColorPallete.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        mColorPallete.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mColorAdapter = new ColorAdapter(getApplicationContext(), colorDataSet);
        mColorPallete.setAdapter(mColorAdapter);
    }
}
