package service.controller;

import dataservice.entity.Fin;
import dataservice.serviceimpl.FinService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by D on 2016/12/25.
 */
public class FinController extends ControllerBase {
    public void deposit_in(Map<String,String> params, OutputStream outputStream){
        Fin fin = FinService.getFinService().findById(Integer.parseInt(params.get("userid")));
        int num = Integer.parseInt(params.get("in_num"));
        HashMap<String,String> map = new HashMap<>();
        try {
            if(fin.getCash()-num >= 0){
                fin.setCash(fin.getCash()-num);
                fin.setCurrentDeposit(fin.getCurrentDeposit()+num);
                FinService.getFinService().update(fin);
                map.put("status","true");
            }else{
                map.put("status","现金不足");
            }
            map.putAll(obj2Map(fin));
            outputStream.write(map2Json(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deposit_out(Map<String,String> params, OutputStream outputStream){
        Fin fin = FinService.getFinService().findById(Integer.parseInt(params.get("userid")));
        int num = Integer.parseInt(params.get("out_num"));
        HashMap<String,String> map = new HashMap<>();
        try {
            if(fin.getCurrentDeposit()-num >= 0){
                fin.setCash(fin.getCash()+num);
                fin.setCurrentDeposit(fin.getCurrentDeposit()-num);
                FinService.getFinService().update(fin);
                map.put("status","true");
            }else{
                map.put("status","存款余额不足");
            }
            map.putAll(obj2Map(fin));
            outputStream.write(map2Json(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void debt_out(Map<String,String> params, OutputStream outputStream){
        Fin fin = FinService.getFinService().findById(Integer.parseInt(params.get("userid")));
        int num = Integer.parseInt(params.get("out_num"));
        HashMap<String,String> map = new HashMap<>();
        try {
            if(fin.getCash()-num >= 0){
                fin.setCash(fin.getCash()-num);
                fin.setDebt(fin.getDebt()-num);
                FinService.getFinService().update(fin);
                map.put("status","true");
            }else{
                map.put("status","现金余额不足");
            }
            map.putAll(obj2Map(fin));
            outputStream.write(map2Json(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
