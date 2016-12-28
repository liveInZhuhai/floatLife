package service.controller;

import dataservice.entity.Event;
import service.main.ServerCache;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by D on 2016/12/28.
 */
public class ActionController extends ControllerBase {
    public void moveto(Map<String, String> params, OutputStream outputStream) {
        int placeId = Integer.parseInt(params.get("placeid"));
        int userId = Integer.parseInt(params.get("userid"));
        ServerCache sc = ServerCache.getCache();
        Map<Integer,Event> eventMap = sc.getEventMap();
        int num = (int)Math.ceil(Math.random()*eventMap.size());
        Event e = eventMap.get(num);
        double gl = Math.random();
        HashMap<String,String> resultMap = new HashMap<>();
        if(gl>e.getEventRate()){
            resultMap.put("status","true");
            resultMap.put("eventName",e.getEventName());
            resultMap.put("eventNote",e.getEventNote());
        }else{
            resultMap.put("status","false");
        }
        try{
            outputStream.write(map2Json(resultMap));
        }catch (Exception E){
            E.printStackTrace();
        }

    }
}
