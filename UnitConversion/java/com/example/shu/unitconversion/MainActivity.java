package com.example.shu.unitconversion;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
        
                //事件回调，修改组件内容
                final Handler myHandler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                                if (msg.what == 0x123) {
                                        TextView textView = (TextView)findViewById(R.id.resultText);
                                        String result = String.valueOf(msg.obj);
                                        textView.setText(result);
                                }
                        }
                };
                
                //第一步，选择换算类型
                getType();
                
                //获取菜单
                final Spinner spinner = (Spinner)findViewById(R.id.spinner);
                //获取编辑框对象
                final EditText editText = (EditText)findViewById(R.id.editText);
                
                //对按钮添加监听事件
                Button connect = (Button)findViewById(R.id.connect);
                connect.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                //获取类型
                                String ofType = spinner.getSelectedItem().toString();
                                //获取值
                                String numStr = editText.getText().toString();
                                Log.i("文本框中获取的值为", numStr);
                                
                                if (!"".equals(numStr.trim())) {
                                        Double num = Double.valueOf(numStr);
                                        Log.i("输入值为", numStr);
                                        
                                        Thread thread = new Thread(new CalculationThread(num, ofType, myHandler));
                                        thread.start();
                                } else {
                                        Toast.makeText(MainActivity.this, "请先输入数据，在进行换算", Toast.LENGTH_SHORT).show();
                                }
                        }
                });
        }
        
        //获取类型，并设置第二个spinner的转换类型
        private void getType() {
                //第一个选择框
                final Spinner spinner = (Spinner)findViewById(R.id.spinner);
                //第二个文本框
                final TextView textResult = (TextView) findViewById(R.id.to);
                //为第一个选择框添加事件,同时确定第二个文本框的内容
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position == 0 || position == 1) {
                                        textResult.setText(TypeResultEnum.CM.getResult());
                                } else if (position == 2) {
                                        textResult.setText(TypeResultEnum.KG.getResult());
                                } else if (position == 3) {
                                        textResult.setText(TypeResultEnum.KILOMETERS.getResult());
                                } else if (position == 4) {
                                        textResult.setText(TypeResultEnum.MI.getResult());
                                }
                        }
        
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                                
                        }
                 });
        }
}
