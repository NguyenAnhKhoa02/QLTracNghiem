package BLL.DTO;

import java.util.ArrayList;

import BLL.manageExamPaper;
import BLL.manageStudent;
import BLL.manageSubject;
import DAL.manageExamPaperDAL;

public class ExamPaperDTO {
    public ExamPaperDTO() {
        mep_manageExamPaper = new manageExamPaper();
        ms_manaageStudent = new manageStudent();
        mepd_manageExamPaperDAL = new manageExamPaperDAL();
        ms_manageSubject = new manageSubject();
    }

    public String[][] getStringArrStatic(String Semester) {
        ArrayList<String> arL_idStudent = ms_manaageStudent.getAllIdStudent();
        String[][] strArr_statistic = new String[arL_idStudent.size()][7];

        int row = 0;
        int column = -1;
        int mark;

        for (String str : arL_idStudent) {
            column = -1;
            strArr_statistic[row][++column] = ms_manaageStudent.getStudentById(str).getId();
            strArr_statistic[row][++column] = ms_manaageStudent.getStudentById(str).getFullName();

            for (String s : ms_manageSubject.getStrArrSubject()) {
                strArr_statistic[row][++column] = mepd_manageExamPaperDAL.getMarkByIdSubjectAndStudent(s.split("-")[0],
                        str, Semester);
            }

            strArr_statistic[row][++column] = String.valueOf(mep_manageExamPaper.getAverageMark(str, Semester));

            mark = mep_manageExamPaper.getAverageMark(str, Semester);
            if (mark >= 8) {
                strArr_statistic[row][++column] = "Giỏi";
            }

            else if (mark >= 6 && mark < 8) {
                strArr_statistic[row][++column] = "Khá";
            }

            else if (mark >= 4 && mark < 6) {
                strArr_statistic[row][++column] = "Trung bình";
            } else
                strArr_statistic[row][++column] = "Yếu";

            row++;
        }

        return strArr_statistic;

    }

    private manageExamPaper mep_manageExamPaper;
    private manageStudent ms_manaageStudent;
    private manageExamPaperDAL mepd_manageExamPaperDAL;
    private manageSubject ms_manageSubject;
}
