package BLL.ExamPaper;

public class DetailExamPaper {
    public DetailExamPaper(String Id, String Answer, String idQuestion) {
        str_Id = Id;
        str_Answer = Answer;
        str_idQuestion = idQuestion;
    }

    public String getId() {
        return str_Id;
    }

    public String getAnswer() {
        return str_Answer;
    }

    public String getIdQuestion() {
        return str_idQuestion;
    }

    private String str_Id;
    private String str_Answer;
    private String str_idQuestion;
}
