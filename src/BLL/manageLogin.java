package BLL;

import DAL.connectSQL;

public class manageLogin {
    public manageLogin(){
        connectSQL = new connectSQL();
    }

    public String isLogin(String username, String pass){
        return connectSQL.isLogin(username, pass);
    }

    private connectSQL connectSQL;
}
