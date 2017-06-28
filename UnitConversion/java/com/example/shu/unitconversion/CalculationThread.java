package com.example.shu.unitconversion;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by shu on 2017/6/18.
 * 将线程任务独立
 */

class CalculationThread implements Runnable{
        private static JSONObject jsonObject;
        //用于事件回调
        private Handler myHandler;
        
        public CalculationThread(double nums, String ofType, Handler myHandler) {
                this.myHandler = myHandler;
                jsonObject = new JSONObject();
                try {
                        jsonObject.put("nums", nums);
                        jsonObject.put("ofType", ofType);
                } catch (JSONException e) {
                        e.printStackTrace();
                }
        }
        
        @Override
        public void run() {
                try {
                        Socket socket = new Socket("192.168.124.22", 9999);
                        //向服务端发数据
                        outputToServer(socket);
                        //客户端读取数据
                        Double nums = inputFromServer(socket);
                        //将数据给向UI线程
                        Message message = new Message();
                        message.what = 0x123;
                        message.obj = nums;
                        myHandler.sendMessage(message);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        
        //向服务端发数据
        private void outputToServer(Socket socket) {
                try {
                        OutputStream outputStream = socket.getOutputStream();
                        String jsons = jsonObject.toString();
                        outputStream.write(jsons.getBytes());
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        
        //读取服务端数据
        private Double inputFromServer(Socket socket) {
                try {
                        InputStream inputStream = socket.getInputStream();
                        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
                        char[] chars = new char[1024];
                        int len = reader.read(chars);
                        String receive = new String(chars, 0, len);
                        Log.i("接收到服务端的数据", receive);
                        
                        JSONObject object = new JSONObject(receive);
                       Double result = (Double)object.get("nums");
                        
                        return result;
                } catch (IOException e) {
                        e.printStackTrace();
                } catch (JSONException e1) {
                        e1.printStackTrace();
                }
                
                return null;
        }
}
