package service.main;

import dataservice.entity.Event;
import dataservice.entity.Item;
import dataservice.entity.Place;
import dataservice.serviceimpl.EventListService;
import dataservice.serviceimpl.ItemService;
import dataservice.serviceimpl.PlaceService;

import java.util.HashMap;
import java.util.Map;

/**
 *  服务器缓存类
 *      使用单例模式  进行数据库操作前首先在缓存中查找
 * Created by D on 2016/12/22.
 */
public class ServerCache {
    private Map<Integer,Item> itemMap;
    private Map<Integer,Place> placeMap;
    private Map<Integer,Event> eventMap;
    private static ServerCache sc = null;
    public ServerCache getCache(){
        return sc;
    }

    public static void serverCacheInitlaizer(){
        sc = new ServerCache();
    }

    private ServerCache(){
        System.out.println("-------数据初始化-------");

        itemInit();

        eventInit();

        placeInit();

        System.out.println("--------加载完成--------");
    }

    private void itemInit(){
        System.out.println("------加载物品数据------");
        itemMap = new HashMap<>();
        for(Item it:ItemService.getItemService().findAll()){
            itemMap.put(it.getId(),it);
        }
        System.out.println("总物品数:" + itemMap.size());
    }

    private void eventInit(){
        System.out.println("------加载事件数据------");
        eventMap = new HashMap<>();
        for(Event event: EventListService.getEventListService().findAll()){
            eventMap.put(event.getId(),event);
        }
        System.out.println("总事件数:" + eventMap.size());
    }

    private  void placeInit(){
        System.out.println("------加载地点数据------");
        placeMap = new HashMap<>();
        for(Place pl: PlaceService.getPlaceService().findAll()){
            placeMap.put(pl.getId(),pl);
        }
        System.out.println("总地点数:" + placeMap.size());
    }

    public Item getItem(int id){
        return itemMap.get(id);
    }

    public Event getEvent(int id){
        return eventMap.get(id);
    }

    public Place getPlace(int id){
        return placeMap.get(id);
    }
}
