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
    public void hospital(Map<String, String> params, OutputStream outputStream) {
        //结果dictionary
        HashMap<String,String> resultMap = new HashMap<>();


        int userId = Integer.parseInt(params.get("userid"));

        FinService fs = FinService.getFinService();
        Fin fi = fs.findById(userId);


        if(fi.getCash()>500){
            resultMap.put("status","true");
            fi.setCash(fi.getCash()-500);
            PlayerService ps = PlayerService.getPlayerService();
            Player pl = ps.findById(userId);
            pl.setHealth(100);
            fs.update(fi);
            ps.update(pl);
        }else{
            resultMap.put("status","啊哦 现金不够了");
        }



        try{
            outputStream.write(map2Json(resultMap));
        }catch (Exception E){
            E.printStackTrace();
        }

    }
    public void cp(Map<String, String> params, OutputStream outputStream) {
        //结果dictionary
        HashMap<String,String> resultMap = new HashMap<>();

        int userId = Integer.parseInt(params.get("userid"));
        int num1 = Integer.parseInt(params.get("num1"));
        int num2 = Integer.parseInt(params.get("num2"));
        int num3 = Integer.parseInt(params.get("num3"));
        int num4 = Integer.parseInt(params.get("num4"));
        FinService fs = FinService.getFinService();
        Fin fi = fs.findById(userId);


        if(fi.getCash()>200){
            int r1,r2,r3,r4;
            r1 = (int)(Math.random()*10);
            r2 = (int)(Math.random()*10);
            r3 = (int)(Math.random()*10);
            r4 = (int)(Math.random()*10);
            resultMap.put("num1",""+r1);
            resultMap.put("num2",""+r2);
            resultMap.put("num3",""+r3);
            resultMap.put("num4",""+r4);
            if(num1 == r1&&num2 == r2&&num3 == r3&&num4 == r4){
                resultMap.put("status","true");
                fi.setCash(fi.getCash()-200+100000);
            }else{
                resultMap.put("status","很遗憾 没有中奖");
                fi.setCash(fi.getCash()-200);
            }


            fs.update(fi);

        }else{
            resultMap.put("status","啊哦 现金不够了");
        }



        try{
            outputStream.write(map2Json(resultMap));
        }catch (Exception E){
            E.printStackTrace();
        }

    }
}
