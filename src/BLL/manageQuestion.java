package BLL;

import java.util.ArrayList;

import BLL.Question.Question;
import DAL.connectSQL;

public class manageQuestion {
    public manageQuestion() {
        conSQL_question = new connectSQL();
    }

    private void sortQuestion(String[][] str_data, int row, int column) {
        for (int i = 0; i < str_data.length; i++) {
            for (int j = i + 1; j <= str_data.length - 1; j++) {
                if (Integer.parseInt(str_data[i][0]) > Integer.parseInt(str_data[j][0])) {
                    String[][] strArr_tempt = new String[1][column];

                    for (int a = 0; a < column; a++) {
                        strArr_tempt[0][a] = str_data[i][a];
                        str_data[i][a] = str_data[j][a];
                        str_data[j][a] = strArr_tempt[0][a];
                    }
                }
            }
        }
    }

    private String[][] convertArrListToArr(ArrayList<?> arrayList) {
        int row = arrayList.size();
        int col = conSQL_question.getColumn() - 1;

        String[][] str_tempt = new String[row][col];
        for (int i_row = 0; i_row < row; i_row++) {
            int j_col = 0;
            str_tempt[i_row][j_col] = ((Question) arrayList.get(i_row)).getIdQuestion();
            str_tempt[i_row][++j_col] = ((Question) arrayList.get(i_row)).getLevelQuestion();
            str_tempt[i_row][++j_col] = ((Question) arrayList.get(i_row)).getContentQuestion();
            str_tempt[i_row][++j_col] = ((Question) arrayList.get(i_row)).getAnswerQuestion();
            str_tempt[i_row][++j_col] = ((Question) arrayList.get(i_row)).getSubjectQuestion();
            str_tempt[i_row][++j_col] = ((Question) arrayList.get(i_row)).getLectureQuestion();
            str_tempt[i_row][++j_col] = ((Question) arrayList.get(i_row)).getTypeQuestion();
        }

        sortQuestion(str_tempt, row, col);

        return str_tempt;
    }

    public ArrayList<Question> getQuestion(String Id) {
        return conSQL_question.getQuestion(Id);
    }

    public String[][] getAllQuestions() {
        return convertArrListToArr(conSQL_question.getAllQuestions(conSQL_question.BOTH));
    }

    // public ArrayList<?> getAllQuesitons() {
    // return conSQL_question.getAllQuestions(conSQL_question.BOTH);
    // }

    // public ArrayList<?> getOptionsQuestions() {
    // return conSQL_question.getAllQuestions(conSQL_question.OPTIONS_QUESTION);
    // }

    // public ArrayList<?> getYesNoQuestions() {
    // return conSQL_question.getAllQuestions(conSQL_question.YESNO_QUESTION);
    // }

    private connectSQL conSQL_question;
    private String Id;

}
