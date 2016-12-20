package urlsmaptest;



import service.urlsmap.Router;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admini on 2016/12/19.
 */
public class URLSMapTest {
    public static void main(String[] args) throws ClassNotFoundException {

        Map map = new HashMap();
        OutputStream outputStream = null;
        Router.directMethod("/login",map,outputStream);

    }

}
