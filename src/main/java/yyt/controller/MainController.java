package yyt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hadoop on 16-8-2.
 */
@Controller
@RequestMapping("/")
public class MainController  {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        System.out.println("#######################  index  #######################");
        return "index";
    }
    @RequestMapping(value = "/welcomeYYT",method = RequestMethod.GET)
    public String welcomeYYT(){
        System.out.println("#######################  welcomeYYT  #######################");
        return "welcomeYYT";
    }
}
