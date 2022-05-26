package BLL.LectureLogin;

public class LectureLogin {
    public LectureLogin(String userName, String Pass){
        str_userName = userName;
        str_Pass = Pass;
    }

    public String getStr_Pass() {
        return str_Pass;
    }

    public String getStr_userName() {
        return str_userName;
    }

    private String str_userName;
    private String str_Pass;
}
