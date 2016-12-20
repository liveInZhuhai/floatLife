package service.urlsmap;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Admini on 2016/12/19.
 *
 * use Class to analyse the url and direct to the class.method
 */
public class Router {

    /**
     * in order to use destination method
     * @param url in order to get url to direct to the class.method
     * @param map the param(Map) of the class.method(destination method)
     * @param outputStream the param(OutputStream) of the class.method(destination method)
     */
    public static void directMethod(String url,Map<String,String> map,OutputStream outputStream){
        String[] classAndMethod = URLMap.URLs.get(url).toString().split("#");  //get destination class and destination method [for example : packet.class#method=>{[packet.class],[method]}]
        try {
            Class cl = Class.forName(classAndMethod[0]);                       //get the direction class
            Method method = cl.getDeclaredMethod(classAndMethod[1], Map.class, OutputStream.class);//get the direction method
            method.invoke(cl.newInstance(),map,outputStream);                  //run the direction method
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
