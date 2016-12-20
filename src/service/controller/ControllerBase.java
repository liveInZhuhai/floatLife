package service.controller;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admini on 2016/12/20.
 * 控制器基类
 */
public abstract class ControllerBase {
    /**
     *
     * @param map
     * @return map into json之后的bytes
     */
    public byte[] getJson(Map map){
        return ("{\"c2dictionary\":true,\"data\":"+ JSON.toJSONString(map) + "}").getBytes();
    }

    public Map<String,String> getReturnMap (Object object){
        Class objClass = object.getClass();
        Map<String,String> map = new HashMap();
        Field fields[] = objClass.getDeclaredFields();
        for (Field field:fields){
            try {
                field.setAccessible(true);
                Object obj = field.get(object);
                if (obj==null){
                    map.put(field.getName(),"");
                }
                else{
                    map.put(field.getName(),obj.toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return map;
    }

}
