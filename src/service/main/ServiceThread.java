package service.main;

import service.urlsmap.Router;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 服务端服务线程类
 * Created by D on 2016/12/20.
 */
public class ServiceThread extends Thread {
    private static String serverName;
    private Socket client;
    private int requestMethod;
    private String queryUrl,requestBody;
    private BufferedReader br;
    //变长字符串数组 用于存放请求信息
    private ArrayList<String> requestSet = new ArrayList<>();
    private Map<String,String> paramMap;
    private OutputStream os;
    private boolean hasBody = false;
    static {
        serverName = "zhFloat";
    }

    public ServiceThread(Socket client) {
        this.client = client;
    }

    /**
     * 读取文件内容，转化为byte数组
     * @param filename 文件名
     * @return
     * @throws IOException
     */
    public  byte[] getFileByte(String filename) throws IOException {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        File file=new File(filename);
        FileInputStream fis=new FileInputStream(file);
        byte[] b=new byte[1000];
        int read;
        while((read=fis.read(b))!=-1)
        {
            baos.write(b,0,read);
        }
        fis.close();
        baos.close();
        return baos.toByteArray();
    }


    /**
     * 根据用户请求的资源类型，设定http响应头的信息，主要是判断用户请求的文件类型（html、jpg...）
     * @param queryResource  如传入参数为jsonHead则返回json头
     * @return 请求头的二进制数组
     */
    private byte[] getHead(String queryResource) {
        if(queryResource.equals("jsonHead")){
            return ("HTTP/1.0 200 OK\nServer:"+serverName+"\nCache-Control: no-cache, no-store, max-age=0\nContent-Type: application/json; charset=utf-8\n\n").getBytes();
        }
        int index=queryResource.lastIndexOf(".");
        String fileType=queryResource.substring(index+1);
        String head= "HTTP/1.0 200 OK\n";
        if(fileType.equals("html")) {
            head +=  "Content-Type:text/html\n" + "Server:"+serverName+"\n" ;
        }
        else if(fileType.equals("jpg")||fileType.equals("gif")||fileType.equals("png")) {
            head +=  "Content-Type:image/jpeg\n";
        }
        else if(fileType.equals("js")) {
            head +=  "Content-Type:application/x-javascript\n";
        }
        else if(fileType.equals("ico"))
        {
            head +=  "Content-Type:image/x-icon\n";
        }
        else if (fileType.equals("css")){
            head +=  "Content-Type:text/css\n";
        }else {
            head +=  "Content-Type:text\n";
        }

        head += "Server:"+serverName+"\n";

        head += "Cache-Control: no-cache\n" +  "\n";

        return head.getBytes();


    }


    private void getRequestLineInfo(){
        String[] temp = requestSet.get(0).split(" ");

        if(temp[0].equals("GET")){
            requestMethod = 0;
        }else if(temp[0].equals("POST")){
            requestMethod = 1;
        }
        queryUrl = temp[1];
    }

    private  void getRequestBody() throws IOException{
        StringBuilder requestB = new StringBuilder("");
        int a;

        while((a = br.read())!=41){
            requestB.append((char)a);
        }

        requestBody = requestB.toString();
        System.out.println(requestBody);
    }

    private boolean isStaticResource(){
        if(queryUrl.length()<=1){
            queryUrl += "index.html";
            return true;
        }
        int temp = queryUrl.lastIndexOf('/');
        temp = queryUrl.indexOf(".",temp);
        if(temp == -1){
            return false;
        }
        return true;
    }

    private boolean isGet(){
        return requestMethod == 0;
    }

    public void staticResourceOutput() throws IOException{

        os.write(getHead(queryUrl));
        os.write(getFileByte("webapp" + queryUrl));
    }

    @Override
    public void run() {

        try {
            InputStream is = client.getInputStream();
            //使用inputStream初始化BufferedReader 准备进行请求的读取
            br = new BufferedReader(new InputStreamReader(is));
            //取得OutPutStream并将引用保存
            os = client.getOutputStream();




            String temp;
            //读取请求详细信息
            while((temp=br.readLine())!=null){
                requestSet.add(temp);

                if(temp.contains("Content-Length:")){
                    hasBody = true;
                }
                else if (temp.equals("")){
                    break;
                }
            }

            if(hasBody){
                getRequestBody();
                parseParams();
            }


            //获取请求行中的信息 并传入类变量中
            getRequestLineInfo();


            //判断访问方式是否为Get
            if(isGet()){
                if(isStaticResource()){
                    //将静态资源写出到输出流
                    staticResourceOutput();
                }
            }else{
                os.write(getHead("jsonHead"));
                Router.directMethod(queryUrl,paramMap,os);
            }


            os.write("\n".getBytes());
            os.write("\n".getBytes());
            os.flush();
            br.close();
            os.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseParams() {
        Pattern pattern = Pattern.compile("(.*?)=(.*?);");
        Matcher matcher = pattern.matcher(requestBody);
        paramMap = new HashMap<>();
        while(matcher.find()){
            paramMap.put(matcher.group(1),matcher.group(2));
        }
    }


}
