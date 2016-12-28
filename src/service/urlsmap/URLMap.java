package service.urlsmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Y on 2016/12/19.
 * use the hashMap to save the urls route
 */
public class URLMap {

    public static Map<String,String> URLs = new HashMap();
    static {
        URLs.put("/login","service.controller.AccountController#login");
        URLs.put("/register","service.controller.AccountController#register");
        URLs.put("/getfin","service.controller.InfoController#getfin");
        URLs.put("/getbag","service.controller.InfoController#getbag");
        URLs.put("/playerinfo","service.controller.InfoController#playerinfo");
        URLs.put("/deposit_in","service.controller.FinController#deposit_in");
        URLs.put("/deposit_out","service.controller.FinController#deposit_out");
        URLs.put("/debt_out","service.controller.FinController#debt_out");
        URLs.put("/moveto","service.controller.ActionController#moveto");
        URLs.put("/hospital","service.controller.ActionController#hospital");
    }
}
