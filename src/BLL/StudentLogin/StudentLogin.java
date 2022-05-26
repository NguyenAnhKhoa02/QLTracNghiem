package BLL.StudentLogin;

public class StudentLogin {
    public StudentLogin(String UserName, String Pass){
        str_userName = UserName;
        str_Pass = Pass;
    }

    public String getUserName(){
        return str_userName;
    }

    public String getPass(){
        return str_Pass;
    }

    private String str_userName;
    private String str_Pass;
}