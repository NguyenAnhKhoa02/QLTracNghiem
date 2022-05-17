package BLL.Question;

public class Question {
    public Question(String Id, String Level, String Content, String Answer, String Subject, String Lecture, String Type,
            String Options) {
        str_Id = Id;
        str_Level = Level;
        str_Content = Content;
        str_Answer = Answer;
        str_Subject = Subject;
        str_Lecture = Lecture;
        str_Options = Options;
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

    public String getOptionsQuestion() {

        return str_Options;
    }

    private String str_Id;
    private String str_Level;
    private String str_Content;
    private String str_Answer;
    private String str_Subject;
    private String str_Lecture;
    private String str_Options;
}
