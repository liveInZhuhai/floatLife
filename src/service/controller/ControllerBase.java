package service.controller;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * Created by Admini on 2016/12/20.
 */
public abstract class ControllerBase {
    public byte[] getJson(Map map){
        return ("{\"c2dictionary\":true,\"data\":"+ JSON.toJSONString(map) + "}").getBytes();
    }
}
