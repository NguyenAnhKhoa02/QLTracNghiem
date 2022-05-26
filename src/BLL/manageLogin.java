package BLL;

import DAL.manageLoginDAL;

public class manageLogin {
    public manageLogin() {
        mld_manageLoginDAL = new manageLoginDAL();
    }

    public String isLogin(String username, String pass) {
        return mld_manageLoginDAL.isLogin(username, pass);
    }

    private manageLoginDAL mld_manageLoginDAL;
}
