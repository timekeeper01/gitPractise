package com.example.study9_lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 这个界面测试的是oncreate方法和onDestroy方法
 */
public class MainActivity extends AppCompatActivity {
    private EditText mContentEt;
    private Button mSendBtn;

    private SharedPreferences mSharedPreferences;

    private static final  String TAG="MainActivity";
    private static final String MESSAGE_RECORD = "message_record";
    private static final String RECORD_KEY = "record_key";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
        mSharedPreferences=this.getSharedPreferences(MESSAGE_RECORD,MODE_PRIVATE);
        String record=mSharedPreferences.getString(RECORD_KEY,null);//后面参数表示默认值
        if (!TextUtils.isEmpty(record)){
            mContentEt.setText(record);
        }


    }

    private void initListener() {
        mSendBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //获取到短信内容
               String content= mContentEt.getText().toString().trim();
               if (TextUtils.isEmpty(content)){
                   Toast.makeText(MainActivity.this,"请输入内容",Toast.LENGTH_SHORT).show();
                   return;
               }
                Log.d(TAG,"发送内容="+content);
            }
        });
    }

    private void initView() {
        mContentEt=findViewById(R.id.content_et);
        mSendBtn=findViewById(R.id.send_btn);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在界面销毁的时候保存数据
        String content= mContentEt.getText().toString().trim();
        SharedPreferences.Editor editor=mSharedPreferences.edit();
        editor.putString(RECORD_KEY,content);
        editor.commit();
    }
}
