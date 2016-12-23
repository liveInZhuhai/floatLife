package entityintomaptest;


import service.controller.AccountController;


/**
 * Created by D on 2016/12/20.
 */
public class Array2JsonTest {
    public static void main(String[] args) {
        AccountController loginController = new AccountController();
        Integer[][][] a = new Integer[1][10][3];
        a[0][0][0] = 1;
        loginController.array2Json(a);
    }
}
