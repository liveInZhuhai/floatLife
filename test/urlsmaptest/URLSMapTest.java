package urlsmaptest;

import urlsmap.AnalyseURL;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admini on 2016/12/19.
 */
public class URLSMapTest {
    public static int aa = 0;
    public void aaa(Map map, OutputStream outputStream){
        System.out.println("55555555555555555555555555");
    }
    public static void main(String[] args) throws ClassNotFoundException {

        Map map = new HashMap();
        OutputStream outputStream = null;
      AnalyseURL.directMethod("/login",map,outputStream);

    }

}
