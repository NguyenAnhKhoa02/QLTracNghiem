package BLL.ExamPaper;

public class ExamPaper {
    public ExamPaper(String idStudent, String idExam, String Id, int numberRight, int numberWrong, float Mark,
            DetailExamPaper detailExamPaper) {
        str_idStudent = idStudent;
        str_idExam = idExam;
        str_Id = Id;
        int_numberRight = numberRight;
        int_numberWrong = numberWrong;
        float_Mark = Mark;
        dep_detailExamPaper = detailExamPaper;
    }

    public ExamPaper(String idStudent, String idExam, String Id, int numberRight, int numberWrong, float Mark) {
        str_idStudent = idStudent;
        str_idExam = idExam;
        str_Id = Id;
        int_numberRight = numberRight;
        int_numberWrong = numberWrong;
        float_Mark = Mark;
    }

    public String getIdStudent() {
        return str_idStudent;
    }

    public String getIdExam() {
        return str_idExam;
    }

    public String getId() {
        return str_Id;
    }

    public int getNumberWrong() {
        return int_numberWrong;
    }

    public int getNumberRight() {
        return int_numberRight;
    }

    public float getMark() {
        return float_Mark;
    }

    public DetailExamPaper getDetailExamPaper() {
        return dep_detailExamPaper;
    }

    private String str_idStudent;
    private String str_idExam;
    private String str_Id;
    private int int_numberRight;
    private int int_numberWrong;
    private float float_Mark;
    private DetailExamPaper dep_detailExamPaper;
}
