package BLL.Subject;

public class Subject {
    public Subject(String Id, String Name, int Credit) {
        str_Id = Id;
        str_Name = Name;
        int_Credit = Credit;
    }

    public Subject(String Id, String Name) {
        str_Id = Id;
        str_Name = Name;
    }

    public String getId() {
        return str_Id;
    }

    public String getName() {
        return str_Name;
    }

    public int getCredit() {
        return int_Credit;
    }

    private String str_Id;
    private String str_Name;
    private int int_Credit;
}
