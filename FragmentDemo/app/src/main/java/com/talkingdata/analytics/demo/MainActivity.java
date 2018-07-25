
package com.talkingdata.analytics.demo;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.tendcloud.tenddata.TCAgent;

public class MainActivity extends Activity {
    private Button button_eventWithId;
    private Button button_eventWithIdAndLabel;
    private Button button_eventWithIdAndLabelAndExtraData;
    private Button button_error;
    private Button button_crash;
    private Button button_fragment;
    private Button button_fragment_hideshow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setListener();
    }
    
    @SuppressWarnings("unused")
    private void thirdPartyServiceTest(){
    }

    private void initViews() {
        button_eventWithId = (Button) findViewById(R.id.button_eventWithId);
        button_eventWithIdAndLabel = (Button) findViewById(R.id.button_eventWithIdAndLabel);
        button_eventWithIdAndLabelAndExtraData = (Button)findViewById(R.id.button_eventWithIdAndLabelAndExtraData);
        button_error = (Button) findViewById(R.id.button_error);
        button_crash = (Button) findViewById(R.id.button_crash);
        button_fragment = (Button) findViewById(R.id.button_fragment);
        button_fragment_hideshow = (Button) findViewById(R.id.button_fragment_hideshow);
    }

    private void setListener() {
        button_eventWithId.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                TCAgent.onEvent(MainActivity.this, "eventWithId");
                Toast.makeText(MainActivity.this, "记录自定义事件：仅 id", Toast.LENGTH_LONG).show();
            }
        });

        button_eventWithIdAndLabel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                TCAgent.onEvent(MainActivity.this, "eventWithIdAndLabel", "事件标签");
                Toast.makeText(MainActivity.this, "记录自定义事件：id 和 label",
                        Toast.LENGTH_SHORT).show();
            }
        });
        
        button_eventWithIdAndLabelAndExtraData.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // 应用市场中游戏的下载情况：游戏类型，下载次数，价格
                // Map的Value只能是字符串（String）和数字（Number）类型，并且一次事件最多只支持10个参数
                double number = Math.random()*(Math.random()>0.5?1:-1);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("游戏类型", "益智游戏");
                map.put("下载次数", 100000);
                map.put("price", number);
                TCAgent.onEvent(MainActivity.this, "eventWithIdAndLabelAndExtraData", "活动-活动详情-点击返回-你是孩子的大白吗？", map);
                Toast.makeText(MainActivity.this, "记录自定义事件： id、 label 以及更多 extra 数据",
                        Toast.LENGTH_SHORT).show();
            }
        });

        button_error.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    int[] array = new int[5];
                    array[6] = 10;
                } catch (Throwable e) {
                    TCAgent.onError(MainActivity.this, e);
                    Toast.makeText(MainActivity.this, "记录错误",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_crash.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // Will cause index out of range
                int[] array = new int[5];
                array[6] = 10;
            }
        });

        button_fragment.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FMActivity.class);
                startActivity(intent);
            }
        });

        button_fragment_hideshow.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FragmentHideShow.class);
                startActivity(intent);
            }
        });
    }
    
    @Override 
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to 
        if (requestCode == 1) {
            // Make sure the request was successful 
            if (resultCode == RESULT_OK) {
                // The user picked a contact. 
                // The Intent's data Uri identifies which contact was selected. 
     
                // Do something with the contact here (bigger example below) 
            } 
        } 
    } 

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
