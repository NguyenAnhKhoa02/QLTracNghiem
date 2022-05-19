package BLL;

import java.util.ArrayList;

import BLL.Question.OptionsQuestion;
import BLL.Question.Question;
import BLL.Question.YesNoQuestion;
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

    private String[][] convertArrOptionsQuestionListToArr(ArrayList<OptionsQuestion> arrayList) {
        int row = arrayList.size();
        int col = conSQL_question.getColumn();

        String[][] str_tempt = new String[row][col];
        for (int i_row = 0; i_row < row; i_row++) {
            int j_col = 0;
            str_tempt[i_row][j_col] = arrayList.get(i_row).getIdQuestion();
            str_tempt[i_row][++j_col] = arrayList.get(i_row).getLevelQuestion();
            str_tempt[i_row][++j_col] = arrayList.get(i_row).getContentQuestion();
            str_tempt[i_row][++j_col] = arrayList.get(i_row).getAnswerQuestion();
            str_tempt[i_row][++j_col] = arrayList.get(i_row).getSubjectQuestion();
            str_tempt[i_row][++j_col] = arrayList.get(i_row).getLectureQuestion();
            str_tempt[i_row][++j_col] = arrayList.get(i_row).getTypeQuestion();
            str_tempt[i_row][++j_col] = arrayList.get(i_row).getOptionsQuestion();
        }

        sortQuestion(str_tempt, row, col);

        return str_tempt;
    }

    private String[][] convertArrYesNoQuestionListToArr(ArrayList<YesNoQuestion> arrayList) {
        int row = arrayList.size();
        int col = conSQL_question.getColumn();

        String[][] str_tempt = new String[row][col];
        for (int i_row = 0; i_row < row; i_row++) {
            int j_col = 0;
            str_tempt[i_row][j_col] = arrayList.get(i_row).getIdQuestion();
            str_tempt[i_row][++j_col] = arrayList.get(i_row).getLevelQuestion();
            str_tempt[i_row][++j_col] = arrayList.get(i_row).getContentQuestion();
            str_tempt[i_row][++j_col] = arrayList.get(i_row).getAnswerQuestion();
            str_tempt[i_row][++j_col] = arrayList.get(i_row).getSubjectQuestion();
            str_tempt[i_row][++j_col] = arrayList.get(i_row).getLectureQuestion();
            str_tempt[i_row][++j_col] = arrayList.get(i_row).getTypeQuestion();
            str_tempt[i_row][++j_col] = arrayList.get(i_row).getOptionsQuestion();
        }

        sortQuestion(str_tempt, row, col);

        return str_tempt;
    }

    private String[][] mertArray(String[][] strArr1, String[][] strArr2) {
        int row = strArr1.length + strArr2.length;
        int column = 8;

        String data[][] = new String[row][column];

        for (int i = 0; i < 2; i++) {
            for (int i_row = 0; i_row < row; i_row++) {
                for (int i_column = 0; i_column < column; i_column++) {
                    data[i_row][i_column] = strArr1[i_row][i_column];
                }
            }
        }

        sortQuestion(data, row, column);

        return data;
    }

    public String[][] getAllQuestions() {
        return mertArray(convertArrOptionsQuestionListToArr(conSQL_question.getOptionsQuestion()),
                convertArrYesNoQuestionListToArr(conSQL_question.getYesNoQuestion()));
    }

    private connectSQL conSQL_question;
}
