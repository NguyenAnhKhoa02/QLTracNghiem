package BLL.Student;

public class Student {
    public Student(String Id, String FullName, String Birthday, String Field) {
        str_Id = Id;
        str_Birthday = Birthday;
        str_Field = Field;
        str_fullName = FullName;
    }

    public String getId() {
        return str_Id.trim();
    }

    public String getFullName() {
        return str_fullName.trim();
    }

    public String getBirthday() {
        return str_Birthday.trim();
    }

    public String getField() {
        return str_Field.trim();
    }

    private String str_Id;
    private String str_fullName;
    private String str_Birthday;
    private String str_Field;
}
