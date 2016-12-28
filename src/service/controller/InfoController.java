package service.controller;

import dataservice.entity.Backpack;
import dataservice.entity.Fin;
import dataservice.entity.Player;
import dataservice.serviceimpl.BackpackService;
import dataservice.serviceimpl.FinService;
import dataservice.serviceimpl.PlayerService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * 
 * Created by D on 2016/12/23.
 */
public class InfoController extends ControllerBase {
    public void getfin(Map<String, String> params, OutputStream outputStream) {

        try {
            outputStream.write(map2Json(obj2Map(FinService.getFinService().findById(Integer.parseInt(params.get("userid"))))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getbag(Map<String, String> params, OutputStream outputStream) {
        Backpack bp = BackpackService.getBackpackService().findById(Integer.parseInt(params.get("userid")));
        try {
            outputStream.write(array2Json(bp.getItemArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playerinfo(Map<String, String> params, OutputStream outputStream) {
        Player pl = PlayerService.getPlayerService().findById(Integer.parseInt(params.get("userid")));
        Fin fin = FinService.getFinService().findById(Integer.parseInt(params.get("userid")));
        try {
            Map<String,String> map = obj2Map(pl);
            map.putAll(obj2Map(fin));
            outputStream.write(map2Json(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

