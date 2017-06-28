import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shu on 2017/6/19.
 * 服务端
 */

public class Server {
        public static void main(String[] args) {
                try {
                        ServerSocket serverSocket = new ServerSocket(9999);
                        System.out.println("服务端开启了" +serverSocket.getInetAddress().toString());
                       while (true) {
                               Socket socket = serverSocket.accept();
                               //获取客户端数据
                               Double fromApp = getAppContext(socket);
                               
                               System.out.println("客户端发送数据" + fromApp.toString());
                               
                               //计算后返回客户端
                               resultOfClient(socket, fromApp);
                       }
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        
        //获取客户端数据
        private static Double getAppContext(Socket socket) {
                try {
                        InputStream inputStream = socket.getInputStream();
                        InputStreamReader reader = new InputStreamReader(inputStream);
                        char[] chars = new char[1024];
                        int len = reader.read(chars);
                        String str = new String(chars, 0, len);
                        System.out.println("客户端" + socket.getInetAddress());
                        System.out.println("来自客户端的数据" + str);

                        if (isGoodJson(str)) {
                                //使用Gson解析字符串
                                Gson gson = new Gson();
                                HashMap map = gson.fromJson(str, HashMap.class);

                                Double nums = (Double)map.get("nums");
                                String ofType = (String) map.get("ofType");
                                Double result = unitConversion(nums, ofType);
                                return result;
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return null;
        }
        
        //向客户端返回结果
        private static void resultOfClient(Socket socket, Double nums) {
                try {
                        OutputStream outputStream = socket.getOutputStream();
                        Gson gson = new Gson();
                        Map<String, Double> map = new HashMap();
                        map.put("nums", nums);
                        String numStr = gson.toJson(map);
                        System.out.println(numStr);
                        outputStream.write(numStr.getBytes());
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        
        //单位换算
        private static Double unitConversion(Double nums, String ofType) {
                if (ofType != null) {
                        switch (ofType) {
                                case  "英寸" : {
                                        return nums * 2.54;
                                }
                                case "寸" : {
                                        return nums * 3.333;
                                }
                                case "英镑" : {
                                        return nums * 0.45;
                                }
                                case "英里" : {
                                        return nums * 1.609;
                                }
                                case "盎司" : {
                                        return nums * 29.573;
                                }
                        }
                }
                return null;
        }

        private static boolean isGoodJson(String json) {
                if (StringUtils.isBlank(json)) {
                        return false;
                }
                try {
                        new JsonParser().parse(json);
                        return true;
                } catch (JsonParseException e) {
                        return false;
                }
        }
}
