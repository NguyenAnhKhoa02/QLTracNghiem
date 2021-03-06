package BLL;

import java.util.ArrayList;

import BLL.DTO.ExamDTO;
import BLL.Exam.DetailExam;
import BLL.Exam.Exam;
import BLL.TimeExam.TimeExam;
import DAL.manageExamDAL;

public class manageExam {
    public manageExam() {
        mnde_manageDetailExam = new manageDetailExam();
        ;
        med_manageExamDAL = new manageExamDAL();
    }

    private String[] mergeStrArrQuestion(String[]... strArr) {
        int lenght = 0;
        for (String[] str : strArr) {
            lenght += str.length;
        }

        String[] str_tempt = new String[lenght];
        int index = 0;
        for (String[] str : strArr) {
            for (String s : str) {
                str_tempt[index] = s;
                index++;
            }
        }

        return str_tempt;
    }

    public void makingNewExam(int RateLV1, int RateLV2, int RateLV3, int RateLV4, int numberQuestion, int numberId,
            TimeExam timeExam, String subject) {
        arL_exam = new ArrayList<>();
        mnde_manageDetailExam = new manageDetailExam();
        rie_randomIdExam = new randomIdExam(numberId, subject);

        for (String str : rie_randomIdExam.getIdExam()) {
            rQ_randomQuestion = new randomQuestion(RateLV1, RateLV2, RateLV3, RateLV4, numberQuestion, numberId,
                    subject);
            edto_examDTO = new ExamDTO(
                    mergeStrArrQuestion(rQ_randomQuestion.getRandomQuestionLV1(),
                            rQ_randomQuestion.getRandomQuestionLV2(),
                            rQ_randomQuestion.getRandomQuestionLV3(), rQ_randomQuestion.getRandomQuestionLV4()));
            arL_exam.add(
                    new Exam(str, timeExam, mnde_manageDetailExam.getAllDetailExam(str, edto_examDTO.getStrArrId())));
        }
    }

    public void saveToSql(String IdExam, ArrayList<DetailExam> detailExams, String TimeName, String Semester) {
        med_manageExamDAL.saveExam(IdExam, detailExams, TimeName, Semester);
    }

    public void saveToSql(ArrayList<Exam> exam) {
        med_manageExamDAL.saveExam(exam);
    }

    public ArrayList<Exam> getAllExam() {
        return arL_exam;
    }

    public ArrayList<String> getAllIdExamBySubject(String Subject) {
        return med_manageExamDAL.getAllIdExamBySubject(Subject);
    }

    public ArrayList<String> getAllIdExamDisplay() {
        return med_manageExamDAL.getAllIdExamDisplay();
    }

    public Exam getExamById(String Id) {
        return med_manageExamDAL.getExamById(Id);
    }

    public Exam getRandExam(String subject) {
        if (med_manageExamDAL.getAllExamBySubject(subject).size() == 0)
            return null;

        rie_randomIdExam = new randomIdExam(med_manageExamDAL.getAllExamBySubject(subject));

        String str_idRand = rie_randomIdExam.getRandExam();
        mte_manageTimeExam = new manageTimeExam();

        return new Exam(str_idRand, mte_manageTimeExam.getTimeExamObject(),
                mnde_manageDetailExam.getAllDetailExamById(str_idRand));
    }

    private randomQuestion rQ_randomQuestion;
    private randomIdExam rie_randomIdExam;
    private Exam exam;
    private manageDetailExam mnde_manageDetailExam;
    private ExamDTO edto_examDTO;
    private String[] strArr_strRand;
    private ArrayList<Exam> arL_exam;

    private manageTimeExam mte_manageTimeExam;
    private manageExamDAL med_manageExamDAL;
}
