package urlsmaptest;



import service.urlsmap.Router;
import service.urlsmap.URLMap;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Y on 2016/12/19.
 */
public class URLSMapTest {
    public static void main(String[] args) throws ClassNotFoundException {
        URLMap.URLs.put("/test","urlsmaptest.aa#aaa");
        Map map = new HashMap();
        OutputStream outputStream = null;
        Router.directMethod("/test",map,outputStream);

    }

}
