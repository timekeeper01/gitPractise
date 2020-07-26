package com.example.study9_lifecycle;
/**
 * onstart+onstop
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class VideoPlayerActivity extends AppCompatActivity {

    private Button mPlayerBtn;
    private TextView mCurrentStatusTv;

    private boolean isPlayer=false;
    private boolean isStopAuto=false;

    private static final String TAG = "VideoPlayerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        mPlayerBtn=findViewById(R.id.player_btn);
        mCurrentStatusTv=findViewById(R.id.current_status_tv);

        mPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlayer){
                    stopPlay();
                }else {
                    startPlay();
                }
            }
        });

    }

    private void stopPlay() {
        Log.d(TAG,"停止播放");
        isPlayer=true;
        mCurrentStatusTv.setText("电影已经停止播放");
        mPlayerBtn.setText("开始");
    }

    private void startPlay() {
        Log.d(TAG,"开始播放");
        isPlayer=true;
        mCurrentStatusTv.setText("电影已经开始播放");
        mPlayerBtn.setText("暂停");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onstart====");
        if(isStopAuto&&!isPlayer){
            startPlay();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onstop====");
        if (isPlayer){
            stopPlay();
            isStopAuto=true;
        }
    }
}


