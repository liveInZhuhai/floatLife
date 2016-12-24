package service.controller;

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
}
