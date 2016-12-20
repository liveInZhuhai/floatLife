package entityintomaptest;


import service.controller.LoginController;



/**
 * Created by D on 2016/12/20.
 */
public class Array2JsonTest {
    public static void main(String[] args) {
        LoginController loginController = new LoginController();
        Integer[][][] a = new Integer[1][10][3];
        a[0][0][0] = 1;
        loginController.array2Json(a);
    }
}
