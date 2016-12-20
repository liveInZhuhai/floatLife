package service.urlsmap;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Admini on 2016/12/19.
 */
public class Router {



    public static void directMethod(String url,Map<String,String> map,OutputStream outputStream){
        String[] classAndMethod = URLMap.URLs.get(url).toString().split("#");
        try {
            Class cl = Class.forName(classAndMethod[0]);
            Method method = cl.getDeclaredMethod(classAndMethod[1], Map.class, OutputStream.class);
            method.invoke(cl.newInstance(),map,outputStream);
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
