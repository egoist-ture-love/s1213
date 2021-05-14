package ltd.egoist.test2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",7777);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("你好呀服务器".getBytes());
        socket.shutdownOutput();
        System.out.println("==========开始接收服务器回写的数据========");
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[8192];
        int len;
        while((len = inputStream.read(bytes))!= -1){
            System.out.println(new String(bytes,0,len));
        }
        socket.close();
    }
}
