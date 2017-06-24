package com.produce101ledlight;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_image)          ImageView image;
    @BindView(R.id.color_pallete)       RecyclerView mColorPallete;
    @BindView(R.id.layout_settings)     RelativeLayout mLayoutSettings;
    @BindView(R.id.speed_txt)           TextView speedTextView;

    @Override
    public void onBackPressed() {
        if(mLayoutSettings.getVisibility() == View.VISIBLE) {
            closeSettings();
            return;
        }
        super.onBackPressed();
    }

    @OnClick(R.id.btn_cancel)
    public void closeSettings() {
        mLayoutSettings.setVisibility(View.GONE);
    }

    @OnClick(R.id.ic_settings)
    public void showSettings(){
        if(mLayoutSettings.getVisibility() == View.VISIBLE){
            closeSettings();
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
            R.color.yellow, R.color.green, R.color.blue,
            R.color.pink, R.color.purple, R.color.deepblue,
            R.color.lightblue, R.color.lightgreen,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mLayoutSettings.setVisibility(View.GONE);
        setColorPallete();

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }else{
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public void setColorPallete(){
        mColorPallete.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        mColorPallete.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mColorAdapter = new ColorAdapter(getApplicationContext(), colorDataSet);
        mColorPallete.setAdapter(mColorAdapter);

        setPalleteClickListener(mColorPallete);
        image.setBackgroundColor(getResources().getColor(colorDataSet[0]));
    }

    private void setPalleteClickListener(RecyclerView recyclerView) {
        final GestureDetector gestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && gestureDetector.onTouchEvent(e)) {
                    int pos = rv.getChildAdapterPosition(child);
                    image.setBackgroundColor(getResources().getColor(colorDataSet[pos]));
                }
                return false;
            }
            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {}
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
        });
    }
}
