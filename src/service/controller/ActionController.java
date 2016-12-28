package service.controller;

import dataservice.entity.Backpack;
import dataservice.entity.Event;
import dataservice.entity.Fin;
import dataservice.entity.Player;
import dataservice.serviceimpl.BackpackService;
import dataservice.serviceimpl.FinService;
import dataservice.serviceimpl.PlayerService;
import service.main.ServerCache;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by D on 2016/12/28.
 */
public class ActionController extends ControllerBase {
    public void moveto(Map<String, String> params, OutputStream outputStream) {
        //结果dictionary
        HashMap<String,String> resultMap = new HashMap<>();

        //获取所需参数及资源
        ServerCache sc = ServerCache.getCache();
        Map<Integer,Event> eventMap = sc.getEventMap();
        int placeId = Integer.parseInt(params.get("placeid"));
        int userId = Integer.parseInt(params.get("userid"));
        PlayerService ps = PlayerService.getPlayerService();
        Player pl = ps.findById(userId);


        //时间推移
        pl.setDayCount(pl.getDayCount() + 1);

        //随机事件判断及插入
        int randomNum = (int)Math.ceil(Math.random()*eventMap.size());
        Event e = eventMap.get(randomNum);
        double gl = Math.random();
        if(gl>e.getEventRate()){
            resultMap.put("status","true");
            resultMap.put("eventName",e.getEventName());
            resultMap.put("eventNote",e.getEventNote());

            FinService fs = FinService.getFinService();
            Fin fi = fs.findById(userId);
            BackpackService bs = BackpackService.getBackpackService();
            Backpack bp = bs.findById(userId);

            e.work(pl,fi,bp);

            fs.update(fi);
            bs.update(bp);
        }else{
            resultMap.put("status","false");
        }

        //TODO 此处需要填充出售物品的数组json
        resultMap.put("sellarray","=========================");

        ps.update(pl);
        try{
            outputStream.write(map2Json(resultMap));
        }catch (Exception E){
            E.printStackTrace();
        }

    }
}
