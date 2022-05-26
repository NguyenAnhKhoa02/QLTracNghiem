package BLL.Question;

public class Question {
    public Question(String Id, String Level, String Content, String Answer, String Subject, String Lecture,
            String Type) {
        str_Id = Id;
        str_Level = Level;
        str_Content = Content;
        str_Answer = Answer;
        str_Subject = Subject;
        str_Lecture = Lecture;
        str_Type = Type;
    }

    public Question (String Content) {
        str_Content = Content;
    }

        public Question() {
    }

    public String getIdQuestion() {
        return str_Id;
    }

    public String getLevelQuestion() {
        return str_Level;
    }

    public String getContentQuestion() {
        return str_Content;
    }

    public String getAnswerQuestion() {
        return str_Answer;
    }

    public String getSubjectQuestion() {
        return str_Subject;
    }

    public String getLectureQuestion() {
        return str_Lecture;
    }

    public String getTypeQuestion() {
        return str_Type;
    }

    public void setStr_Answer(String str_Answer) {
        this.str_Answer = str_Answer;
    }

    public void setStr_Content(String str_Content) {
        this.str_Content = str_Content;
    }

    public void setStr_Id(String str_Id) {
        this.str_Id = str_Id;
    }

    public void setStr_Lecture(String str_Lecture) {
        this.str_Lecture = str_Lecture;
    }

    public void setStr_Level(String str_Level) {
        this.str_Level = str_Level;
    }

    public void setStr_Subject(String str_Subject) {
        this.str_Subject = str_Subject;
    }

    public void setStr_Type(String str_Type) {
        this.str_Type = str_Type;
    }

    private String str_Id;
    private String str_Level;
    private String str_Content;
    private String str_Answer;
    private String str_Subject;
    private String str_Lecture;
    private String str_Type;
}
