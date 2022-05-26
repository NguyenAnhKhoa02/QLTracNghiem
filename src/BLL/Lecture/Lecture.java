package BLL.Lecture;

public class Lecture {

    public Lecture() {

    }

    private String Id;
    private String Name;
    private String Filed;

    public Lecture(String Id, String Name, String Field) {
        str_Id = Id;
        str_Name = Name;
        str_Filed = Field;
    }

    public Lecture(String Id, String Name, String Field,String Position) {
        str_Id = Id;
        str_Name = Name;
        str_Filed = Field;
        str_Position = Position;
    }

    public String getStr_Id() {
        return str_Id;
    }

    public String getStr_Name() {
        return str_Name;
    }

    public String getStr_Filed() {
        return str_Filed;
    }

    public String getStr_Position() {
        return str_Position;
    }

    private String str_Id;
    private String str_Name;
    private String str_Filed;
    private String str_Position;
}
