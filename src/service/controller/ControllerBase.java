package service.controller;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admini on 2016/12/20.
 * 控制器基类
 * Edit by D on 2016/12/21
 * 增加array2Json
 */
public abstract class ControllerBase {
    /**
     *map2Json
     * @param map 将转换成json的map对象
     * @return map into json之后的bytes
     */
    public byte[] map2Json(Map map){
        return ("{\"c2dictionary\":true,\"data\":"+ JSON.toJSONString(map) + "}").getBytes();
    }

    /**
     * obj2map
     * @param object 任意对象
     * @return 对象被解析后生成的 变量名--变量值 map
     */
    public Map<String,String> obj2Map(Object object){
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

    /**
     * array2Json
     * @param array 因construct2限制  只可传入三维数组
     * @return 转换完成后的可被construct2中array读入的json二进制数组
     */
    public byte[] array2Json(Object array[][][]){
        int x = array.length;
        int y = array[0].length;
        int z = array[0][0].length;

        String jg = "{\"c2array\":true,\"size\":["+ x +","+ y +","+ z +"],\"data\":"+ JSON.toJSONString(array) +"}";

        return jg.getBytes();
    }

    /**
     * array2Json
     * @param array 二维数组
     * @return 将二维数组封装成为三维数组后的construct2数组二进制json
     */
    public byte[] array2Json(Object array[][]){
        Object[][][] arr = new Object[1][][];
        arr[0] = array;
        return array2Json(arr);
    }

    /**
     * array2Json
     * @param array 一维数组
     * @return 将一维数组封装成为三维数组后的construct2数组二进制json
     */
    public byte[] array2Json(Object array[]){
        Object[][][] arr = new Object[1][1][];
        arr[0][0] = array;
        return array2Json(arr);
    }
}
