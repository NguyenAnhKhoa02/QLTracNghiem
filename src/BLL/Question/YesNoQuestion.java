package BLL.Question;

public class YesNoQuestion extends Question {

    public YesNoQuestion(String Id, String Level, String Content, String Answer, String Subject, String Lecture,
            String Type, String Options) {
        super(Id, Level, Content, Answer, Subject, Lecture, Type);
        str_options = Options;
    }

    public YesNoQuestion(String Content, String Options) {
        super(Content);
        str_options = Options;
    }

    public String getOptionsQuestion() {
        str_options = "a.Đúng\nb.Sai";
        return str_options;
    }

    private String str_options;
}
