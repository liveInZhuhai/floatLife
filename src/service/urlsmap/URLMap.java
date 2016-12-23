package service.urlsmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Y on 2016/12/19.
 * use the hashMap to save the urls route
 */
public class URLMap {

    public static Map URLs = new HashMap();
    static {
        URLs.put("/login","service.controller.LoginController#login");
        URLs.put("/register","service.controller.LoginController#register");

    }
}
