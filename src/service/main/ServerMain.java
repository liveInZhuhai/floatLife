package service.main;
/**
 *
 * Created by D on 2016/10/29.
 */


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    public static void main(String[] args) throws IOException {
        //绑定端口配置
        int port=80;


        if(args.length>0)
            port=Integer.valueOf(args[0]);

        //开始服务
        new ServerMain().start(port);

    }

    /**
     * 在指定端口启动http服务器
     * @param port 指定的端口
     * @throws IOException
     */
    public void start(int port) throws IOException {

        ServerSocket server = new ServerSocket(port);
        System.out.println("开始监听端口"+port+"...........");

        //使用一个true循环实现不断监听
        while (true) {
            //当有访问时获取socket对象
            Socket client = server.accept();
            //新建服务线程
            ServiceThread serverthread = new ServiceThread(client);
            //启动线程
            serverthread.start();
        }
    }
}