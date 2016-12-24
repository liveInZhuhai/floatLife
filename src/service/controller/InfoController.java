package service.controller;

import dataservice.entity.Backpack;
import dataservice.serviceimpl.BackpackService;
import dataservice.serviceimpl.FinService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by D on 2016/12/23.
 */
public class InfoController extends ControllerBase {
    public void getfin(Map<String,String> params, OutputStream outputStream){

        try {
            outputStream.write(map2Json(obj2Map(FinService.getFinService().findById(Integer.parseInt(params.get("userid"))))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getbag(Map<String,String> params, OutputStream outputStream){
        Backpack bp = BackpackService.getBackpackService().findById(Integer.parseInt(params.get("userid")));
        try {
            outputStream.write(array2Json(bp.getItemArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
